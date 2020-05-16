package se.nackademin.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
public class LoggerBean {
    static final Logger log2 = LoggerFactory.getLogger(LoggerBean.class);
}
