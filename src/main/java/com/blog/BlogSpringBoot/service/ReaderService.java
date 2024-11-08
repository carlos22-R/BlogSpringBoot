package com.blog.BlogSpringBoot.service;

import com.blog.BlogSpringBoot.dto.ReaderDTO;
import com.blog.BlogSpringBoot.dto.UserDTO;
import com.blog.BlogSpringBoot.entity.Reader;
import com.blog.BlogSpringBoot.entity.User;
import com.blog.BlogSpringBoot.repository.ReadersRepository;
import com.blog.BlogSpringBoot.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReaderService{
    public final ReadersRepository readerRepository;
    public final UsuarioRepository usuarioRepository;

    @Autowired
    public ReaderService( ReadersRepository readerRepository, UsuarioRepository usuarioRepository) {

        this.readerRepository = readerRepository;
        this.usuarioRepository = usuarioRepository;
    }
    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
    public Optional<Reader> getReaderById(int id) {
        return readerRepository.findById(id);
    }
    public Reader saveReader(ReaderDTO readerDTO) {
        UUID uuid = UUID.randomUUID();
        Reader reader = new Reader();
        reader.setName(readerDTO.getName());
        reader.setEmail(readerDTO.getEmail());
        reader.setId((int)uuid.getMostSignificantBits()&Integer.MAX_VALUE);
        return readerRepository.save(reader);
    }
    @Transactional
    public void deleteReaderById(int id) {

        usuarioRepository.deleteById(id);
        readerRepository.deleteById(id);
    }
    public Reader updateReader(int id,ReaderDTO readerDTO) {
        Reader reader = readerRepository.findById(id).orElseThrow(()->new InvalidParameterException("Invalid id"));
        reader.setName(readerDTO.getName());
        reader.setEmail(readerDTO.getEmail());
        return readerRepository.save(reader);
    }

    public Optional<Reader> getReaderByName(String name) {
        return readerRepository.findByName(name);
    }

    public void saveUser(UserDTO userDTO) {
        User usuario= new User();
        UUID uuid = UUID.randomUUID();
        usuario.setId((int)uuid.getMostSignificantBits()&Integer.MAX_VALUE);
        usuario.setPassword(userDTO.getPassword());
        usuario.setUsername(userDTO.getUsername());
        Reader reader = new Reader();
        reader.setName(userDTO.getName());
        reader.setEmail(userDTO.getEmail());
        reader.setId((int)uuid.getMostSignificantBits()&Integer.MAX_VALUE);
        usuarioRepository.save(usuario);
        readerRepository.save(reader);
    }
    public UserDTO findUserByReader(String username , String password) {
        Optional<User> user=usuarioRepository.findByUsername(username);
        if (user.isPresent() && user.get().getUsername().equals(username)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(user.get().getUsername());
            userDTO.setId(user.get().getId().toString());
            return userDTO;
        }
        return null;
    }
    public Optional<User> getUserByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
}
