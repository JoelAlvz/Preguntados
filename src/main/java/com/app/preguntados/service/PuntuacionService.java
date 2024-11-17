package com.app.preguntados.service;

import com.app.preguntados.api.IPuntuacionService;
import com.app.preguntados.model.Puntuacion;
import com.app.preguntados.model.dao.PuntuacionDao;
import com.app.preguntados.model.dto.PuntuacionDTO;
import com.app.preguntados.model.dto.dtomapper.PuntuacionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("PuntuacionService")
@Lazy
public class PuntuacionService implements IPuntuacionService {
    @Autowired
    private PuntuacionDao puntuacionDao;
    @Override
    public PuntuacionDTO queryPuntuacion(PuntuacionDTO puntuacionDTO) {
        Puntuacion puntuacion = PuntuacionMapper.INSTANCE.toEntity(puntuacionDTO);
        return PuntuacionMapper.INSTANCE.toDTO(puntuacionDao.getReferenceById(puntuacion.getId()));
    }

    @Override
    public List<PuntuacionDTO> queryAllPuntuaciones() {
        return PuntuacionMapper.INSTANCE.toDTOList(puntuacionDao.findAll());
    }

    @Override
    public int insertPuntuacion(PuntuacionDTO puntuacionDTO) {
        Puntuacion puntuacion = PuntuacionMapper.INSTANCE.toEntity(puntuacionDTO);
        puntuacionDao.saveAndFlush(puntuacion);
        return puntuacion.getId();
    }

    @Override
    public int updatePuntuacion(PuntuacionDTO puntuacionDTO) {
        return insertPuntuacion(puntuacionDTO);
    }

    @Override
    public int deletePuntuacion(PuntuacionDTO puntuacionDTO) {
        int id = puntuacionDTO.getId();
        Puntuacion puntuacion = PuntuacionMapper.INSTANCE.toEntity(puntuacionDTO);
        puntuacionDao.delete(puntuacion);
        return id;
    }
}
