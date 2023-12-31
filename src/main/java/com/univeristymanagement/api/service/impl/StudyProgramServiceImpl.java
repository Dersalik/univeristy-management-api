package com.univeristymanagement.api.service.impl;

import com.univeristymanagement.api.exceptions.ResourceAlreadyAssignedException;
import com.univeristymanagement.api.exceptions.ResourceNotFoundException;
import com.univeristymanagement.api.model.*;
import com.univeristymanagement.api.model.Dto.*;
import com.univeristymanagement.api.repository.*;
import com.univeristymanagement.api.service.StudyProgramService;
import com.univeristymanagement.api.service.mappers.AcademicDegreeMapper;
import com.univeristymanagement.api.service.mappers.StudentStudyProgramMapper;
import com.univeristymanagement.api.service.mappers.StudyProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
/**
 * This class is responsible for implementing the StudyProgramService interface
 */
public class StudyProgramServiceImpl implements StudyProgramService {

    private final StudyProgramRepository studyProgramRepository;
    private final AcademicRepository academicRepository;
    private final AcademicDepartmentRepository academicDepartmentRepository;
    private final AcademicDegreeRepository academicDegreeRepository;
    private final StudentStudyProgramRepository studentStudyProgramRepository;
    private final StudyProgramDegreeRepository studyProgramDegreeRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public StudyProgramServiceImpl(StudyProgramRepository studyProgramRepository
            , AcademicRepository academicRepository
            , AcademicDepartmentRepository academicDepartmentRepository
            , StudyProgramDegreeRepository studyProgramDegreeRepository
            , AcademicDegreeRepository academicDegreeRepository
            , StudentStudyProgramRepository studentStudyProgramRepository, StudentRepository studentRepository) {
        this.studyProgramRepository = studyProgramRepository;
        this.academicRepository = academicRepository;
        this.academicDepartmentRepository = academicDepartmentRepository;
        this.studyProgramDegreeRepository = studyProgramDegreeRepository;
        this.academicDegreeRepository = academicDegreeRepository;
        this.studentStudyProgramRepository = studentStudyProgramRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * this method is responsible for updating a study program
     * @param id
     * @param studyProgramUpdateDto
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto updateStudyProgram(Long id, StudyProgramUpdateDto studyProgramUpdateDto) {
//        Academic academic= academicRepository.findById(studyProgramUpdateDto.getAcademicId())
//                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", studyProgramUpdateDto.getAcademicId()));
//
//        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(studyProgramUpdateDto.getDepartmentId())
//                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", studyProgramUpdateDto.getDepartmentId()));


        StudyProgram studyProgram = studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));

        studyProgram.setName(studyProgramUpdateDto.getName());
        studyProgram.setDescription(studyProgramUpdateDto.getDescription());
        studyProgram.setCapacity(studyProgramUpdateDto.getCapacity());
//        studyProgram.setAcademic(academic);
//        studyProgram.setAcademicDepartment(academicDepartment);

        studyProgramRepository.save(studyProgram);

        return StudyProgramMapper.StudyProgramToDto(studyProgram);



    }

    /**
     *  this method is responsible for getting all study programs
     * @return List<StudyProgramDto>
     */
    @Override
    public List<StudyProgramDto> getAllStudyPrograms() {
        return studyProgramRepository.findAll().stream()
                .map(StudyProgramMapper::StudyProgramToDto)
                .collect(Collectors.toList());
    }
    /**
     * this method is responsible for getting a study program by id
     * @param id
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto getStudyProgramById(Long id) {
        return studyProgramRepository.findById(id)
                .map(StudyProgramMapper::StudyProgramToDto)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));
    }

    /**
     * this method is responsible for deleting a study program by id
     * @param id
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto deleteStudyProgramById(Long id) {
        StudyProgram studyProgram = studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));

        studyProgramRepository.delete(studyProgram);

        return StudyProgramMapper.StudyProgramToDto(studyProgram);
    }

    @Override
    public void addAcademicDegreeToStudyProgram(Long academicDegreeId, Long studyProgramId) {
        academicDegreeExists(academicDegreeId);
        studyProgramExists(studyProgramId);

        studyProgramDegreeRepository.findByAcademicDegreeIdAndStudyProgramId(academicDegreeId, studyProgramId)
                .ifPresent(studyProgramDegree -> {
                    throw new ResourceAlreadyAssignedException("AcademicDegree", academicDegreeId, "StudyProgram", studyProgramId);
                });

        StudyProgramDegree academicStudyProgramDegree = new StudyProgramDegree();
        StudyProgram studyProgram = studyProgramRepository.findById(studyProgramId).get();
        AcademicDegree academicDegree = academicDegreeRepository.findById(academicDegreeId).get();



        academicStudyProgramDegree.setAcademicDegree(academicDegree);
        academicStudyProgramDegree.setStudyProgram(studyProgram);

        studyProgramDegreeRepository.save(academicStudyProgramDegree);
    }

    @Override
    public void deleteAcademicDegreeFromStudyProgram(Long academicDegreeId, Long id) {
        academicDegreeExists(academicDegreeId);
        studyProgramExists(id);

        StudyProgramDegree studyProgramDegree = studyProgramDegreeRepository.findByAcademicDegreeIdAndStudyProgramId(academicDegreeId, id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgramDegree", "academicDegreeId", academicDegreeId));

        studyProgramDegreeRepository.delete(studyProgramDegree);
    }

    @Override
    public void addStudentToStudyProgram(long id, long studentId, StudentStudyProgramCreateDto studentStudyProgramCreateDto) {

        studyProgramExists(id);
        studentExists(studentId);

        studentStudyProgramRepository.findByStudentIdAndStudyProgramId(studentId, id)
                .ifPresent(studentStudyProgram -> {
                    throw new ResourceAlreadyAssignedException("Student", studentId, "StudyProgram", id);
                });

        StudentStudyProgram studentStudyProgram = new StudentStudyProgram();
        StudyProgram studyProgram = studyProgramRepository.findById(id).get();
        Student student = studentRepository.findById(studentId).get();

        studentStudyProgram.setStudent(student);
        studentStudyProgram.setStudyProgram(studyProgram);
        studentStudyProgram.setActive(studentStudyProgramCreateDto.isActive());
        studentStudyProgram.setEnrollmentDate(studentStudyProgramCreateDto.getEnrollmentDate());
        studentStudyProgram.setGraduationDate(studentStudyProgramCreateDto.getGraduationDate());

        studentStudyProgramRepository.save(studentStudyProgram);


    }

    @Override
    public void deleteEnrollmentById(Long studyProgramId, Long studentId){
        studyProgramExists(studyProgramId);
        studentExists(studentId);

        StudentStudyProgram studentStudyProgram = studentStudyProgramRepository.findByStudentIdAndStudyProgramId(studentId, studyProgramId)
                .orElseThrow(() -> new ResourceNotFoundException("StudentStudyProgram", "", 0));

        studentStudyProgramRepository.delete(studentStudyProgram);
    }

    @Override
    public Set<StudentStudyProgramDto> getEnrollmentsByStudyProgramId(Long id) {
        studyProgramExists(id);


        Set<StudentStudyProgram> students = studentStudyProgramRepository.findAllByStudyProgramId(id);


        return students.stream()
                .map(StudentStudyProgramMapper::studentToStudentDto)
                .collect(Collectors.toSet());

    }


    /**
     * this method is responsible for getting all academic degrees by study program id
     * @param id
     * @return Set<AcademicDegreeDto>
     */
    @Override
    public Set<AcademicDegreeDto> getAcademicDegreesByStudyProgramId(Long id) {
        StudyProgram studyProgram = studyProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyProgram", "id", id));


        Set<AcademicDegree> academicDegrees = studyProgramDegreeRepository.findAllByStudyProgramId(id)
                .stream()
                .map(studyProgramDegree -> studyProgramDegree.getAcademicDegree())
                .collect(Collectors.toSet());


        return academicDegrees.stream()
                .map(AcademicDegreeMapper::academicDegreeToAcademicDegreeDto)
                .collect(Collectors.toSet());
    }

    /**
     *  this method is responsible for creating a new study program
     * @param studyProgramDto
     * @return StudyProgramDto
     */
    @Override
    public StudyProgramDto createStudyProgram(StudyProgramCreateDto studyProgramDto) {

        Academic academic= academicRepository.findById(studyProgramDto.getAcademicId())
                .orElseThrow(() -> new ResourceNotFoundException("Academic", "id", studyProgramDto.getAcademicId()));

        AcademicDepartment academicDepartment = academicDepartmentRepository.findById(studyProgramDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("AcademicDepartment", "id", studyProgramDto.getDepartmentId()));

        StudyProgram studyProgram = StudyProgramMapper.StudyProgramCreateDtoToStudyProgram(studyProgramDto
        ,academic,academicDepartment);

        studyProgramRepository.save(studyProgram);


        return StudyProgramMapper.StudyProgramToDto(studyProgram);
    }


    @Override
    public  void academicDegreeExists(Long id){
        if(!academicDegreeRepository.existsById(id)){
            throw new ResourceNotFoundException("Academic Degree","id", id);
        }
    }

    @Override
    public  void studyProgramExists(Long id){
        if(!studyProgramRepository.existsById(id)){
            throw new ResourceNotFoundException("Study Program","id", id);
        }
    }

    @Override
    public  void studentExists(Long id){
        studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

    }
}
