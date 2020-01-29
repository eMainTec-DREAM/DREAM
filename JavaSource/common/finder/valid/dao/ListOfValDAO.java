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
     * code, description �� �˻� �������� 
     * �ý��� �ڵ� ���̺�˻� 
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findSysDirList(ListOfValDTO listOfValDTO,User user);

    /**
     * code, description �� �˻� �������� 
     * ȸ�� �ڵ� ���̺�˻� 
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findCompList(ListOfValDTO listOfValDTO,User user);
    
    /**
     * code, description �� �˻� �������� 
     * ����� �ڵ� ���̺� �˻�
     * @author  javaworker
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUsrDirList(ListOfValDTO listOfValDTO);
    
    /**
     * TAMENU Table���� ��ȸ
     * @author  kim21017
     * @version $Id: ListOfValDAO.java,v 1.7 2015/01/09 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findMenuList(ListOfValDTO listOfValDTO);
    
    /**
     * TACDUSRD Table���� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findCdUsrCdList(ListOfValDTO listOfValDTO);
    
    /**
     * TAUSRGRP Table���� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findUsrGrpList(ListOfValDTO listOfValDTO);
    
    /**
     * TAEMP Table���� ��ȸ
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
     * TADEPT Table���� ��ȸ
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findDeptList(ListOfValDTO listOfValDTO);

    /**
     * TAEQLOC Table���� ��ȸ - ��ġ�з�
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
     * TAEQCTG Table���� ��ȸ - ��������
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
     * â��
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    public List findWcodeList(ListOfValDTO listOfValDTO);
    
    /**
     * TAFIALURE Table���� ��ȸ - ��������
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
     * TAEQLOC Table���� ��ȸ - ��ġ�з�
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
