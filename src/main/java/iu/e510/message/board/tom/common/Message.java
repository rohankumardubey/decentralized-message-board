package iu.e510.message.board.tom.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Message implements Serializable, Comparable<Message> {
    private String message;
    private int processID;
    private int clock;
    private boolean unicast;
    private String ack;
    private String release;
    private String id;
    private List<String> recipients;
    private boolean allAcked;

    public Message(String message, int processID, int clock, boolean unicast) {
        this.message = message;
        this.processID = processID;
        this.clock = clock;
        this.unicast = unicast;
        this.ack = "";
        this.release = "";
        this.id = UUID.randomUUID().toString();
        this.recipients = new ArrayList<>();
        this.allAcked = false;
    }

    public boolean isAllAcked() {
        return allAcked;
    }

    public void setAllAcked(boolean allAcked) {
        this.allAcked = allAcked;
    }

    public String getMessage() {
        return message;
    }

    public int getProcessID() {
        return processID;
    }

    public int getClock() {
        return clock;
    }

    public boolean isUnicast() {
        return unicast;
    }

    public boolean isAck() {
        return !ack.isEmpty();
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public boolean isRelease() {
        return !release.isEmpty();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return message + '\'' + " generated from pid=" + processID + " with clock: " + clock + " unicast: " + unicast;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Message o) {
        if (this.id.equals(o.getId())) {
            return 0;
        }
        // compare clock values first
        if (this.clock > o.clock) {
            return 1;
        } else if (this.clock < o.clock) {
            return -1;
        } else {
            // if clock values are equal, precedence given to process with the smallest pid
            if (this.getProcessID() < o.getProcessID()) {
                return -1;
            } else if (this.getProcessID() > o.getProcessID()) {
                return 1;
            } else {
                // same message
                return 0;
            }
        }
    }
}