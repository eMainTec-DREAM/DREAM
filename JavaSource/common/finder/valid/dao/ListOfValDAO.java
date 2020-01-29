package common.finder.valid.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.finder.valid.dto.ListOfValDTO;

/**
 * List Of Value DAOIml
 * @author  javaworker
 * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
 * @since   1.0
 */
public interface ListOfValDAO
{
    /**
     * code, description 을 검색 조건으로 
     * 시스템 코드 테이블검색 
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findSysDirList(ListOfValDTO listOfValDTO,User user);

    /**
     * code, description 을 검색 조건으로 
     * 회사 코드 테이블검색 
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findCompList(ListOfValDTO listOfValDTO,User user);
    
    /**
     * code, description 을 검색 조건으로 
     * 사용자 코드 테이블 검색
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUsrDirList(ListOfValDTO listOfValDTO);
    
    /**
     * TAMENU Table에서 조회
     * @author  kim21017
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findMenuList(ListOfValDTO listOfValDTO);
    
    /**
     * TACDUSRD Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findCdUsrCdList(ListOfValDTO listOfValDTO);
    
    /**
     * TAUSRGRP Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUsrGrpList(ListOfValDTO listOfValDTO);
    
    /**
     * TAEMP Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findEmpNameList(ListOfValDTO listOfValDTO);
    
    public List findEmpNoList(ListOfValDTO listOfValDTO);
    
    /**
     * TADEPT Table에서 조회
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findDeptList(ListOfValDTO listOfValDTO);

    /**
     * TAEQLOC Table에서 조회 - 위치분류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findEqLocList(ListOfValDTO listOfValDTO);
    
    public List findEqLocFullList(ListOfValDTO listOfValDTO);

    /**
     * TAEQCTG Table에서 조회 - 설비종류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findEqCtgList(ListOfValDTO listOfValDTO);
    
    public List findEqCtgFullList(ListOfValDTO listOfValDTO);
    
    /**
     * 창고
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findWcodeList(ListOfValDTO listOfValDTO);
    
    /**
     * TAFIALURE Table에서 조회 - 설비종류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findFailureList(ListOfValDTO listOfValDTO, User user);
    
    /**
     * YN 
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findYnList(ListOfValDTO listOfValDTO);
    
    /**
     * LEVEL(1,2,3,4)
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findLevelList(ListOfValDTO listOfValDTO);

    /**
     * TAEQLOC Table에서 조회 - 위치분류
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUserList(ListOfValDTO listOfValDTO);
    
    public List findPmTypeList(ListOfValDTO listOfValDTO, User user);

	public List findPlantList(ListOfValDTO listOfValDTO);

	public List findEqToolList(ListOfValDTO listOfValDTO);
	
	public List findCrityList(ListOfValDTO listOfValDTO);
	
	public List findTaskMapList(ListOfValDTO listOfValDTO);

	/**
	 * System Code With Auto Complete Param
	 * @param listOfValDTO
	 * @param user
	 * @param columnMap
	 * @param conditionMap
	 * @return
	 */
	public List findAcSysDirList(ListOfValDTO listOfValDTO, User user, Map<String, String> conditionMap);

}
