package iu.e510.message.board.cluster.data;

import iu.e510.message.board.cluster.BaseZKTest;
import iu.e510.message.board.util.Constants;
import org.apache.commons.lang3.SerializationUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;

public class TestDataManager extends BaseZKTest {
    private String ip = "192.168.1.2";

    @Test
    public void setData() throws Exception {
        DataManager dataManager = new DataManagerImpl(ip);
        dataManager.addData("bloomington", "hi");
        dataManager.addData("iu", "hi");
        HashSet<String> dataManagerAllTopics = dataManager.getAllTopics();

        String dataStore = config.getConfig(Constants.DATA_LOCATION) + "/" + ip;
        HashSet<String> resultSet = SerializationUtils.deserialize(zooKeeper.getData(dataStore));

        Assert.assertEquals(resultSet, dataManagerAllTopics);
    }
}
