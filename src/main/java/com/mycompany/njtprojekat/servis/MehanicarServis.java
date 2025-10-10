package com.mycompany.njtprojekat.servis;

import com.mycompany.njtprojekat.dto.impl.MehanicarDto;
import com.mycompany.njtprojekat.entity.impl.Mehanicar;
import com.mycompany.njtprojekat.mapper.impl.MehanicarMapper;
import com.mycompany.njtprojekat.repository.impl.MehanicarRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class MehanicarServis {

    private final MehanicarRepository mehanicarRepository;
    private final MehanicarMapper mehanicarMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MehanicarServis(
            MehanicarRepository mehanicarRepository,
            MehanicarMapper mehanicarMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.mehanicarRepository = mehanicarRepository;
        this.mehanicarMapper = mehanicarMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // 🔹 Vrati sve mehaničare kao DTO
    public List<MehanicarDto> findAll() {
        return mehanicarRepository.findAll()
                .stream()
                .map(mehanicarMapper::toDto)
                .collect(Collectors.toList());
    }

    // 🔹 Nađi mehaničara po ID-u
    public MehanicarDto findById(Integer id) throws Exception {
        Mehanicar mehanicar = mehanicarRepository.findById(id);
        if (mehanicar == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Mehaničar sa ID " + id + " nije pronađen."
            );
        }
        return mehanicarMapper.toDto(mehanicar);
    }

    // 🔹 Kreiraj novog mehaničara
    public MehanicarDto create(MehanicarDto mehanicarDto) {
        Mehanicar mehanicar = mehanicarMapper.toEntity(mehanicarDto);
        mehanicar.setPassword(passwordEncoder.encode(mehanicar.getPassword()));
        mehanicarRepository.save(mehanicar);
        return mehanicarMapper.toDto(mehanicar);
    }

    // 🔹 Izbriši mehaničara po ID-u
    public void deleteById(Integer id) {
        mehanicarRepository.deleteById(id);
    }

    // 🔹 Ažuriraj mehaničara
    public MehanicarDto update(MehanicarDto mehanicarDto) throws Exception {
        Mehanicar existing = mehanicarRepository.findById(mehanicarDto.getIdMehanicar());
        if (existing == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Mehaničar sa ID " + mehanicarDto.getIdMehanicar() + " nije pronađen."
            );
        }

        existing.setIme(mehanicarDto.getIme());
        existing.setPrezime(mehanicarDto.getPrezime());
        existing.setUsername(mehanicarDto.getUsername());

        // Ako je poslat novi password
        if (mehanicarDto.getPassword() != null && !mehanicarDto.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(mehanicarDto.getPassword()));
        }

        mehanicarRepository.save(existing);
        return mehanicarMapper.toDto(existing);
    }

    // ✅ IMPLEMENTACIJA METODE KOJA NEDOSTAJE
    public Mehanicar findByUsername(String username) {
        Mehanicar mehanicar = mehanicarRepository.findByUsername(username);
        if (mehanicar == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Mehaničar sa korisničkim imenom '" + username + "' nije pronađen."
            );
        }
        return mehanicar;
    }
}
