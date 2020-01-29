package dream.pers.appreq.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqCommonDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * �����û(��û�����ۼ�)
 * @author javaworker
 * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
 * @since 1.0
 */
public interface AppReqDetailDAO extends BaseJdbcDaoSupportIntf
{
    /**
     * ���缱��ȸ
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     * @return
     */
    public List findFlowUserList(AppReqDetailDTO appReqDetailDTO);

    /**
     * ��û���� ����ȸ
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqCommonDTO
     * @return
     */
    public AppReqDetailDTO findMstrDetail(AppReqCommonDTO appReqCommonDTO, User loginUser);

    /**
     * �������� ��� ��� ���·� ��
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    public void updateDetail(AppReqDetailDTO appReqDetailDTO,  User user);

    /**
     * ��� ó���� �Է�
     * @author  javaworker
     * @version $Id: AppReqDetailDAO.java,v 1.2 2014/03/07 05:35:54 javaworker Exp $
     * @since   1.0
     * 
     * @param appReqDetailDTO
     */
    public void insertDetail(AppReqDetailDTO appReqDetailDTO,  User user);

    public void actionReq(AppReqDetailDTO appReqDetailDTO, User user);
    
    public void setApprUsrStatus(AppReqDetailDTO appReqDetailDTO,  User user);

    public String checkUsr(AppReqDetailDTO appReqDetailDTO,  User user);

    public void initApprUsrStatus(AppReqDetailDTO appReqDetailDTO,  User user);
}