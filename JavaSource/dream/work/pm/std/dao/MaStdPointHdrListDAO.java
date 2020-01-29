package dream.work.pm.std.dao;

import java.util.List;

import common.bean.User;
import dream.work.pm.std.dto.MaStdPointCommonDTO;

/**
 * 표준항목 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 */
public interface MaStdPointHdrListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointHdrList(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
    
    /**
     * 표준항목 삭제
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param loginUser
     * @return
     */
    public int deleteList(String id, User loginUser);
    
    /**
   	 * copy detail insert
   	 * @param newId
   	 * @param oldId
   	 * @param user
   	 * @return
   	 */
   	public int insertCopyDetail(String newId, String oldId, String revisionHistId, String revisionStatus, User user, String isCopy);
   	public int insertCopyPoint(String newId, String oldId, User user, String isCopy);
   	public int insertCopyWoType(String newId, String oldId, User user);
   	public int insertCopyWork(String newId, String oldId, User user);
   	public int insertCopyPart(String newId, String oldId, User user);
   	
   	public String findTotalCount(MaStdPointCommonDTO maStdPointCommonDTO, User loginUser);
}