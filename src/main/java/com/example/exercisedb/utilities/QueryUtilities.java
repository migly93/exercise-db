package com.example.exercisedb.utilities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import static com.example.exercisedb.utilities.StringUtilities.cleanBlankStrings;

public class QueryUtilities {

    /**
     * Creates a {@link PageRequest} for queries. <br>
     * Every parameter can be null <br>
     * If properties is null or empty, the returned {@link PageRequest} will not be sorted
     * @param pageNum Page number - DEFAULT: 0
     * @param pageSize Page size - DEFAULT: Integer.MAX_VALUE
     * @param direction Sorting direction - DEFAULT: ASC
     * @param properties Sorting columns
     * @return A {@link PageRequest} for queries
     */
    public static PageRequest createPageRequest(Integer pageNum, Integer pageSize, Direction direction, String... properties) {
        properties = cleanBlankStrings(properties);
        if(pageNum == null) pageNum = 0;
        if(pageSize == null) pageSize = Integer.MAX_VALUE;
        if(properties != null && properties.length > 0) {
            if(direction == null) direction = Direction.ASC;
            return PageRequest.of(pageNum, pageSize, Sort.by(direction, properties));
        }
        return PageRequest.of(pageNum, pageSize);
    }
}
