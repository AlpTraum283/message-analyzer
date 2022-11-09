package liga.medical.messageanalyzer.api.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static liga.medical.common.dto.constant.SystemName.MessageAnalyzer;

@Aspect
@Component
public class LoggerAdvice {
    Logger logger = Logger.getLogger(String.valueOf(LoggerAdvice.class));

    @Around("@annotation(liga.medical.common.dto.annotations.DbLog)")
    public Object serviceLogger(ProceedingJoinPoint pjp) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String id = UUID.randomUUID().toString();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString().replaceAll("class", "");
        Object[] args = pjp.getArgs();

        Object object = null;
        logger.log(Level.INFO, String.format("uid:%s, method name:%s.%s() with args %s, system type: %s", id, className, methodName, mapper.writeValueAsString(args), MessageAnalyzer));
        try {
            object = pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return object;
    }
}
