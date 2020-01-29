package intf.dream.cricket.auth.dao;

import java.util.List;
import java.util.Map;

public interface CricketAuthListDAO {     
    public List findAuthPageList(Map map) throws Exception;
    public List findAuthPgBtnList(Map map) throws Exception;
    public List findAuthPgFieldList(Map map) throws Exception;
    public List findAuthPgGridColList(Map map) throws Exception;
    public List findAuthPgPageList(Map map) throws Exception;
    public List findAuthPgLinkedList(Map map) throws Exception;
}
