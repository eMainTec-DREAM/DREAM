package dream.mgr.message.dao;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;

/**
 * Message Transfer Page - Detail DAO
 * @author syyang
 * @version $Id:$
 * @since 1.0
 *
 */
public interface MgrMessageTransDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * FIND DETAIL
     * @param mgrMessageTransCommonDTO
     * @param user
     * @return
     * @throws Exception
     */
    public MgrMessageTransDetailDTO findDetail(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception;
    
    public int insertDetail(MgrMessageTransDetailDTO mgrMessageTransDetailDTO, User user) throws Exception;
    public int updateResultLog(MgrMessageTransDetailDTO mgrMessageTransDetailDTO, User user) throws Exception;

    public String findMailTitle(String compNo, String errorLogId) throws Exception;

    public List findErrorLog(String errorLogId, User user) throws Exception;

    public String isUseMailCateg(String messageObjectType, String compNo) throws Exception;
    
    public String isUseSmsCateg(String messageObjectType, String compNo) throws Exception;
    
    public String isUseKakaoCateg(String messageObjectType, String compNo) throws Exception;

    public List findNextApprUser(String apprListId, User user) throws Exception;

    public String isUseMailEmp(String messageObjectType, String compNo, String empId) throws Exception;
    
    public String isUseSmsEmp(String messageObjectType, String compNo, String empId) throws Exception;

    public String isUseKakaoEmp(String messageObjectType, String compNo, String empId) throws Exception;

    public List getDataREQ10(String objectId, User user) throws Exception;
    
    public List getMsgSendServCompData(String compNo) throws Exception;

    public List getEmp(Map map, User user) throws Exception;

    public List getDataRQC10(String objectId, User user) throws Exception;

    public List getDataWRK10(String objectId, User user) throws Exception;

    public List getDataPRI10(String objectId, User user) throws Exception;

    public List getDataISS10(String objectId, User user) throws Exception;

    public List getDataQNA20(String objectId, User user) throws Exception;

    public List getDataUSR10(String objectId, User user) throws Exception;

    public List getDataUSR20(String objectId, User user) throws Exception;

    public List getDataPPR10(String objectId, User user) throws Exception;

    public List getSubListPRI10(String objectId, User user) throws Exception;

    public List getApprList(String apprListId, User user) throws Exception;

    public List getDataAPP10_REQWORK(String objectId, User user) throws Exception;

    public List getDataAPP10_PTBUYREQ(String objectId, User user) throws Exception;
    
    public List getSubListAPP10_PTBUYREQ(String objectId, User user) throws Exception;

    public List getDataAPP10_PTSTKTAKE(String objectId, User user) throws Exception;

    public List getDataAPP10_WORKORDER(String objectId, User user) throws Exception;

    public List getApprUsrList(String apprListId, User user) throws Exception;

    public List findApprDrafter(String apprListId, User user) throws Exception;
    
}