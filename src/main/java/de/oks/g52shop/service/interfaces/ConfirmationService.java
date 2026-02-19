package de.oks.g52shop.service.interfaces;

import de.oks.g52shop.domain.entity.User;

public interface ConfirmationService {

    String generateConfirmationCode(User user);
}
