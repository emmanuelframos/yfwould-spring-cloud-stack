package com.goodweather4party.api.validator;

import com.goodweather4party.api.dto.PlaylistFilter;
import com.goodweather4party.exception.BusinessException;
import com.goodweather4party.exception.ExceptionMessages;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PlaylistFilterValidator {

    public void validate(PlaylistFilter filter) throws BusinessException {

        if (Objects.isNull(filter))
            throw new BusinessException(ExceptionMessages.INVALID_PLAYLIST);

        if (filter.hasNoParams())
            throw new BusinessException(ExceptionMessages.INVALID_PARAMS);

        if (filter.hasCityName() && filter.hasLatitudeAndLogitude())
            throw new BusinessException(ExceptionMessages.INVALID_PARAMS);

        if (filter.hasCityName() && (filter.hasLongitude() || filter.hasLatitude()))
            throw new BusinessException(ExceptionMessages.ONLY_CITY_NAME);

        if (filter.hasLatitude() && !filter.hasLongitude())
            throw new BusinessException(ExceptionMessages.MISS_LONGITUDE);

        if (filter.hasLongitude() && !filter.hasLatitude())
            throw new BusinessException(ExceptionMessages.MISS_LATITUDE);
    }
}
