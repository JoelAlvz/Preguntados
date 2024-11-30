package com.app.preguntados.service;

import com.app.preguntados.api.IPreguntaService;
import com.app.preguntados.model.Pregunta;
import com.app.preguntados.model.dao.PreguntaDao;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.dtomapper.PreguntaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("PreguntaService")
@Lazy
public class PreguntaService implements IPreguntaService {
    @Autowired
    private PreguntaDao preguntaDao;

    @Transactional
    @Override
    public PreguntaDTO queryPregunta(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = PreguntaMapper.INSTANCE.toEntity(preguntaDTO);
        return PreguntaMapper.INSTANCE.toDTO(preguntaDao.getReferenceById(pregunta.getId()));
    }
    @Transactional
    @Override
    public List<PreguntaDTO> queryAllPreguntas() {
        return PreguntaMapper.INSTANCE.toDTOList(preguntaDao.findAll());
    }
    @Transactional
    @Override
    public int insertPregunta(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = PreguntaMapper.INSTANCE.toEntity(preguntaDTO);
        preguntaDao.saveAndFlush(pregunta);
        return pregunta.getId();
    }
    @Transactional
    @Override
    public int updatePregunta(PreguntaDTO preguntaDTO) {
        return insertPregunta(preguntaDTO);
    }
    @Transactional
    @Override
    public int deletePregunta(PreguntaDTO preguntaDTO) {
        int id = preguntaDTO.getId();
        Pregunta pregunta = PreguntaMapper.INSTANCE.toEntity(preguntaDTO);
        preguntaDao.delete(pregunta);
        return id;
    }

    @Transactional
    @Override
    public int deletePreguntaByNombre(PreguntaDTO preguntaDTO) {
        String nombre = preguntaDTO.getPregunta();
        Optional<Pregunta> preguntaOptional = preguntaDao.findByPregunta(nombre);

        if (preguntaOptional.isPresent()) {
            preguntaDao.delete(preguntaOptional.get());
            return preguntaOptional.get().getId();
        } else {
            throw new RuntimeException("Pregunta '" + nombre + "' no encontrada.");
        }
    }
}
