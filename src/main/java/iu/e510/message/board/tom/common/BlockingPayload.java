package iu.e510.message.board.tom.common;

import iu.e510.message.board.cluster.data.DataManager;

public interface BlockingPayload {
    Message process(DataManager dataManager);
}