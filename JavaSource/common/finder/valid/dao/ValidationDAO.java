package common.finder.valid.dao;

import java.util.List;

import common.bean.User;

/**
 * Validation DAOIml
 * @author  javaworker
 * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
 * @since   1.0
 */
public interface ValidationDAO
{
    /**
     * TACDSYSD
     * find  Code of Description
     * @author  javaworker
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @return
     */
    public List findSysDirDescCode(String desc, String dirType, User user);
    
    public List findSysDirCode(String code, String dirType, User user);
    
    /**
     * TACDSYSD
     * find  Description of Code 
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @return
     */
    public List findSysDirCodeDesc(String code, String dirType,User user);
    
    /**
     * ������ڵ� ���̺��� �ڵ�� descã��
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @param compNo
     * @return
     */
    public String findUsrDirCodeDesc(String code, String dirType, String compNo);
    
    /**
     * TACDSYSD
     * find  Description of id 
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param code
     * @return
     */
    public List findSysDirIdDesc(String id, User user);
    
    /**
     * ������ڵ� ���̺��� id�� descã��
     * @author  ssong
     * @version $Id $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public String findUsrDirIdDesc(String id, String compNo);
    
    /**
     * TACDUSRD
     * find  Code of Description
     * @author  javaworker
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param code
     * @param dirType
     * @param compNo
     * @return
     */
    public List findUsrDirDescCode(String desc, String dirType, String compNo);
    
    public List findUsrDirCode(String desc, String dirType, String compNo);
    
    /**
     * sequence nextval
     * @author  wondo
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param sequenceName
     * @return
     */
    public String findNextVal(String sequenceName);
    
    /**
     * no nextval(Next No) ���� �ѹ� ��������(ex:eqloc_no)
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 pochul2423 Exp $
     * @since   1.0
     * 
     * @param tableName
     * @param columnName
     * @param compNo
     * @return
     */
    public String findNextNoVal(String tableName, String columnName, String compNo);
    
    /**
     * menu desc �� code �˻�
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 kim21017 Exp $
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findMenuDesc(String desc, String lang);

    /**
     * page desc �� code �˻�
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 kim21017 Exp $
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findPageDesc(String desc);

    /**
     * button desc �� code �˻�
     * @author  kim21017
     * @version $Id: ValidationDAO.java,v 1.20 2014/09/03 04:19:26 kim21017 Exp $
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findButtonDesc(String desc);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEmpNo(String desc, String compNo);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findUserName(String desc, String compNo);
    
    public List findEmpName(String desc, String compNo);
    
    public List findEmpId(String id, String compNo);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param pageId
     * @return
     */
    public List findCdUsrdDesc(String expCode, String desc);
    
    public List findCdUsrdCode(String expCode, String desc);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param pageId
     * @return
     */
    public List findDeptDesc(String desc,String expCode);
    
    public List findDeptCode(String desc, String expCode);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param pageId
     * @return
     */
    public List findUsrGrpDesc(String desc);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @param codeKind
     * @return
     */
    public List findEqLocDesc(String desc, String compNo, String extCode);
    
    public List findEqLocFullDesc(String desc, String compNo, String expCode);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @param codeKind
     * @return
     */
    public List findEqCtgDesc(String desc, String compNo);
    
    public List findEqCtgFullDesc(String desc, String compNo, String expCode);

    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqCtgAsmbDesc(String desc, String expCode,String compNo);

    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @param expCode2
     * @return
     */
    public List findEqAsmbDesc(String desc, String expCode,String compNo, String expCode2);

    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findFailureDesc(String desc, String expCode,String compNo, String lang);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findWcodeDesc(String desc, String expCode,String compNo);
    
    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findTWcodeDesc(String desc, String expCode,String compNo);

    /**
     * desc �� id �˻�
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findVendorDesc(String desc, String expCode,String compNo);
    
    /**
     * desc �� id �˻�(TACTCTR)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @return
     */
    public List findCtCtrDesc(String desc, String compNo);

    /**
     * desc �� id �˻�(TAWKCTR)
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param compNo
     * @return
     */
    public List findWkCtrDesc(String desc, String compNo);
    
    /**
     * key_name���� key_type,key_noã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @param lang
     * @return
     */
    public List findLangDesc(String desc, String expCode,String compNo, String lang);
    
    /**
     * partNo�� partIdã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findPartsDesc(String desc, String expCode,String compNo, String lang);
    
    /**
     * partNo�� partIdã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findTpartsDesc(String desc, String expCode,String compNo);

    /**
     * partNo�� partIdã��2
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findReqPartsDesc(String desc, String expCode,String compNo);
    
    /**
     * �ڻ�ã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqAssetDesc(String desc, String expCode,String compNo);
    
    /**
     * ����������ã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findLineDesc(String desc, String expCode,String compNo);

    /**
     * ������ ǰ�Ǽ� ã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqAppDesc(String desc, String expCode,String compNo);
    
    /**
     * ������� ǰ�Ǽ� ã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findPtAppDesc(String desc, String expCode,String compNo);

    /**
     * MES LINE 
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findMesLineDesc(String desc, String expCode,String compNo);

    /**
     * WO_NO
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * @return
     */
    public List findWoNo(String desc, String expCode,String compNo,String expCode2,String expCode3, String lang);

    /**
     * ����
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqDesc(String desc, String expCode,String compNo, String lang);
    
    public List findEqCode(String desc, String expCode,String compNo, String lang);
    
    /**
     * vendor������ vendorIdã��
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @param expCode
     * @param compNo
     * @return
     */
    public List findEqVendorDesc(String desc, String expCode,String compNo);
    
    /**
     * YN
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findYn(String desc);
    
    /**
     * LEVEL(1,2,3,4)
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param desc
     * @return
     */
    public List findLvl(String desc);
    
    public List findFileAttach(String code, String expCode, User user);
      
    public List findImageAttach(String code, String expCode, User user);

	public List findPlantDesc(String desc, String compNo);
}