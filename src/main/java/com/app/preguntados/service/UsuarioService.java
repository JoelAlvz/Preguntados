package com.app.preguntados.service;

import com.app.preguntados.api.IUsuarioService;
import com.app.preguntados.model.Usuario;
import com.app.preguntados.model.dao.UsuarioDao;
import com.app.preguntados.model.dto.UsuarioDTO;
import com.app.preguntados.model.dto.dtomapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("UsuarioService")
@Lazy
public class UsuarioService implements IUsuarioService {
    @Autowired
    private UsuarioDao usuarioDao;
    @Override
    public UsuarioDTO queryUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);
        return UsuarioMapper.INSTANCE.toDTO(usuarioDao.getReferenceById(usuario.getId()));
    }

    @Override
    @Transactional
    public List<UsuarioDTO> queryAllUsuarios() {
        return UsuarioMapper.INSTANCE.toDTOList(usuarioDao.findAll());
    }

    @Override
    public int insertUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);
        usuarioDao.saveAndFlush(usuario);
        return usuario.getId();
    }

    @Override
    public int updateUsuario(UsuarioDTO usuarioDTO) {
        return insertUsuario(usuarioDTO);
    }

    @Override
    public int deleteUsuario(UsuarioDTO usuarioDTO) {
        int id = usuarioDTO.getId();
        Usuario usuario = UsuarioMapper.INSTANCE.toEntity(usuarioDTO);
        usuarioDao.delete(usuario);
        return id;
    }
}
