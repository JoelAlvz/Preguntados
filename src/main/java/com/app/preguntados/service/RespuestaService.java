package com.app.preguntados.service;

import com.app.preguntados.api.IRespuestaService;


import com.app.preguntados.model.Respuesta;

import com.app.preguntados.model.dao.RespuestaDao;
import com.app.preguntados.model.dto.RespuestaDTO;

import com.app.preguntados.model.dto.dtomapper.RespuestaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("RespuestaService")
@Lazy
public class RespuestaService implements IRespuestaService {
    @Autowired
    private RespuestaDao respuestaDao;

    @Override
    public RespuestaDTO queryRespuesta(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = RespuestaMapper.INSTANCE.toEntity(respuestaDTO);
        return RespuestaMapper.INSTANCE.toDTO(respuestaDao.getReferenceById(respuesta.getId()));
    }

    @Override
    public List<RespuestaDTO> queryAllRespuestas() {
        return RespuestaMapper.INSTANCE.toDTOList(respuestaDao.findAll());
    }

    @Override
    public int insertRespuesta(RespuestaDTO respuestaDTO) {
        Respuesta respuesta = RespuestaMapper.INSTANCE.toEntity(respuestaDTO);
        respuestaDao.saveAndFlush(respuesta);
        return respuesta.getId();
    }

    @Override
    public int updateRespuesta(RespuestaDTO respuestaDTO) {
        return insertRespuesta(respuestaDTO);
    }

    @Override
    public int deleteRespuesta(RespuestaDTO respuestaDTO) {
        int id = respuestaDTO.getId();
        Respuesta respuesta = RespuestaMapper.INSTANCE.toEntity(respuestaDTO);
        respuestaDao.delete(respuesta);
        return id;
    }
}
