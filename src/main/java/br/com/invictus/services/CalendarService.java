package br.com.invictus.services;

import br.com.invictus.data.vo.CalendarVO;
import br.com.invictus.data.vo.StudentVO;
import br.com.invictus.exceptions.ResourceNotFoundException;
import br.com.invictus.mapper.DozerMapper;
import br.com.invictus.model.CalendarModel;
import br.com.invictus.model.StudentModel;
import br.com.invictus.repositories.CalendarRepository;
import br.com.invictus.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CalendarService {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    CalendarRepository calendarRepository;

    public List<CalendarVO> findAll(){
        logger.info("Finding all events.");

        var events = calendarRepository.findAll();

        var calendarVO = DozerMapper.parseListObjects(events, CalendarVO.class);
        return calendarVO;
    }

    public CalendarVO findById(Long id) {
        logger.info("Finding one event.");

        var event = calendarRepository.findById(id);

        if (event.isPresent()) {
            return DozerMapper.parseObject(event.get(), CalendarVO.class);
        } else {
            logger.warning("Event not found for ID: " + id);
            throw  new ResourceNotFoundException("Event not found");
        }
    }

    public List<CalendarVO> findByStudentName(String title){

        logger.info("Finding by title");
        logger.info("Finding by title: {}" + title);

        var eventTitle = calendarRepository.findByTitle("%" + title.trim().toLowerCase() + "%");

        if (eventTitle == null) {
            logger.warning("No user found with name: {}" + eventTitle);
        }

        var studentVO = DozerMapper.parseObject(eventTitle, CalendarVO.class);

        return Collections.singletonList(studentVO);
    }

    public ResponseEntity<?> create(CalendarVO calendarVO) {

        var event = calendarRepository.findByTitle(calendarVO.getTitle());

        try{
            /* Verifica se o usuário já existe
            if (event != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Event exist!");
            }*/

            var calendarModel = DozerMapper.parseObject(calendarVO, CalendarModel.class);
            var savedCalendar = calendarRepository.save(calendarModel);
            CalendarVO calendarVO1 = DozerMapper.parseObject(savedCalendar, CalendarVO.class);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Evento criado com sucesso");
        } catch (Exception e) {
            logger.warning("Erro ao criar evento");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao criar o evento: " + e.getMessage());
        }
    }

    public CalendarVO update(CalendarVO vo) {

        Optional<CalendarModel> existingOptional = calendarRepository.findById(vo.getId());

        if (existingOptional.isEmpty()) {
            throw new ResourceNotFoundException("Evento não encontrado com ID: " + vo.getId());
        }

        CalendarModel existing = existingOptional.get();

        // Atualiza os campos recebidos do frontend, preservando os campos não enviados
        if (vo.getTitle() != null) existing.setTitle(vo.getTitle());
        if (vo.getStart() != null) existing.setStart(vo.getStart());
        if (vo.getEnd() != null) existing.setEnd(vo.getEnd());
        if (vo.getDescription() != null) existing.setDescription(vo.getDescription());

        CalendarModel saved = calendarRepository.save(existing);

        return DozerMapper.parseObject(saved, CalendarVO.class);
    }

    public void delete(Long id) {

        logger.info("Deleting one event!");

        var entity = calendarRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        calendarRepository.delete(entity);
    }

}

