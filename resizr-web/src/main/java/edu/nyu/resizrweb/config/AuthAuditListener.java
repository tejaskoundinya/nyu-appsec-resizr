package edu.nyu.resizrweb.config;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Component to capture spring application events to audit
 */
@Component
@Log4j
public class AuthAuditListener {
    @EventListener
    public void onAuditEvent(AuditApplicationEvent event) {
        AuditEvent auditEvent = event.getAuditEvent();
        // Log only auth events
        if (auditEvent.getType().contains("AUTHENTICATION")) {
            log.info("Login attempt: Principal - " + auditEvent.getPrincipal() + " | " + auditEvent.getType());
            WebAuthenticationDetails details = (WebAuthenticationDetails) event.getAuditEvent().getData().get("details");
            log.info("Session ID: " + details.getSessionId() + ", IP: " + details.getRemoteAddress());
        }
    }
}
