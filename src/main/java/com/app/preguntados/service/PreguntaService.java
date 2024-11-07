package com.app.preguntados.service;

import com.app.preguntados.api.IPreguntaService;
import com.app.preguntados.model.Pregunta;
import com.app.preguntados.model.dao.PreguntaDao;
import com.app.preguntados.model.dto.PreguntaDTO;
import com.app.preguntados.model.dto.dtomapper.PreguntaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PreguntaService")
@Lazy
public class PreguntaService implements IPreguntaService {
    @Autowired
    private PreguntaDao preguntaDao;

    @Override
    public PreguntaDTO queryPregunta(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = PreguntaMapper.INSTANCE.toEntity(preguntaDTO);
        return PreguntaMapper.INSTANCE.toDTO(preguntaDao.getReferenceById(pregunta.getId()));
    }

    @Override
    public List<PreguntaDTO> queryAllPreguntas() {
        return PreguntaMapper.INSTANCE.toDTOList(preguntaDao.findAll());
    }

    @Override
    public int insertPregunta(PreguntaDTO preguntaDTO) {
        Pregunta pregunta = PreguntaMapper.INSTANCE.toEntity(preguntaDTO);
        preguntaDao.saveAndFlush(pregunta);
        return pregunta.getId();
    }

    @Override
    public int updatePregunta(PreguntaDTO preguntaDTO) {
        return insertPregunta(preguntaDTO);
    }

    @Override
    public int deletePregunta(PreguntaDTO preguntaDTO) {
        int id = preguntaDTO.getId();
        Pregunta pregunta = PreguntaMapper.INSTANCE.toEntity(preguntaDTO);
        preguntaDao.delete(pregunta);
        return id;
    }
}
