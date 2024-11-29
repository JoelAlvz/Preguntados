package com.app.preguntados.service;

import com.app.preguntados.api.IRespuestaService;


import com.app.preguntados.model.Pregunta;
import com.app.preguntados.model.Respuesta;

import com.app.preguntados.model.dao.PreguntaDao;
import com.app.preguntados.model.dao.RespuestaDao;
import com.app.preguntados.model.dto.RespuestaDTO;

import com.app.preguntados.model.dto.dtomapper.RespuestaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("RespuestaService")
@Lazy
public class RespuestaService implements IRespuestaService {
    @Autowired
    private RespuestaDao respuestaDao;
    @Autowired
    private PreguntaDao preguntaDao;
    @Transactional
    @Override
    public RespuestaDTO queryRespuesta(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = RespuestaMapper.INSTANCE.toEntity(respuestaDTO);
        return RespuestaMapper.INSTANCE.toDTO(respuestaDao.getReferenceById(respuesta.getId()));
    }
    @Transactional
    @Override
    public List<RespuestaDTO> queryAllRespuestas() {
        return RespuestaMapper.INSTANCE.toDTOList(respuestaDao.findAll());
    }
    @Transactional
    @Override
    public int insertRespuesta(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = RespuestaMapper.INSTANCE.toEntity(respuestaDTO);
        respuestaDao.saveAndFlush(respuesta);
        return respuesta.getId();
    }
    @Transactional
    @Override
    public int updateRespuesta(RespuestaDTO respuestaDTO) {
        return insertRespuesta(respuestaDTO);
    }
    @Transactional
    @Override
    public int deleteRespuesta(RespuestaDTO respuestaDTO) {
        int id = respuestaDTO.getId();
        Respuesta respuesta = RespuestaMapper.INSTANCE.toEntity(respuestaDTO);
        respuestaDao.delete(respuesta);
        return id;
    }

    @Override
    public void insertRespuestaParaPregunta(int preguntaId, RespuestaDTO respuestaDTO) {
        Pregunta pregunta = preguntaDao.findById(preguntaId)
                .orElseThrow(() -> new RuntimeException("Pregunta no encontrada con ID: " + preguntaId));

        // Crear el objeto Respuesta a partir del DTO
        Respuesta respuesta = new Respuesta();
        respuesta.setRespuesta(respuestaDTO.getRespuesta());
        respuesta.setVerdadera(respuestaDTO.getVerdadera());
        respuesta.setPregunta(pregunta);

        // Guardar en la base de datos
        respuestaDao.save(respuesta);
    }


}
