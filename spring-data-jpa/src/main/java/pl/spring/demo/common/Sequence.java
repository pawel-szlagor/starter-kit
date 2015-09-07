package pl.spring.demo.common;

import pl.spring.demo.to.IdAware;

import java.util.Collection;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class Sequence {

    public long nextValue(Collection<? extends IdAware> existingIds) {
        long result = 1;
        for (IdAware nextExistingId : existingIds) {
            if (Long.compare(nextExistingId.getId(), result) > 0) {
                result = nextExistingId.getId();
            }
        }
        return result;
    }
}
