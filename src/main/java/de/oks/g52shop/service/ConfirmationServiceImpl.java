package de.oks.g52shop.service;

import de.oks.g52shop.domain.entity.ConfirmationCode;
import de.oks.g52shop.domain.entity.User;
import de.oks.g52shop.repository.ConfirmationCodeRepository;
import de.oks.g52shop.service.interfaces.ConfirmationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationCodeRepository repository;

    public ConfirmationServiceImpl(ConfirmationCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public String generateConfirmationCode(User user) {
        String code = UUID.randomUUID().toString();
        LocalDateTime expired = LocalDateTime.now().plusMinutes(5);
        ConfirmationCode codeEntity = new ConfirmationCode(code, expired, user);
        repository.save(codeEntity);
        return code;
    }
}
