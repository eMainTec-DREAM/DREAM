package dream.pers.appstb.message.mail.dao;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportIntf;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * ���ϼ����ڼ��� - �� dao
 * 
 * @author kim21017
 * @version $Id: MaMailDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 */
@Deprecated
public interface PersAppstbMessageMailDAO extends BaseJdbcDaoSupportIntf
{
    
	
	
    
    //�۾���û ���� method
    public List selectWoReqDetail(AppReqDetailDTO appReqDetailDTO, User user);
    //W/O ���� method
    public List selectWorkOrderDetail(AppReqDetailDTO appReqDetailDTO, User user);
    
    public String[] findWoReqRecUserMailList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findWoReqRecWkGrpMailList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findWoReqRecDeptMailList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findWoReqRecUserEmpNoList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findWoReqRecWkGrpEmpNoList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findWoReqRecDeptEmpNoList(AppReqDetailDTO appReqDetailDTO, User user);
	
    
    
    //�������� ������
    public String[] findDrafterMailList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findApproverMailList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findDrafterEmpNoList(AppReqDetailDTO appReqDetailDTO, User user);
    public String[] findApproverEmpNoList(AppReqDetailDTO appReqDetailDTO, User user);
    public String findTitle(AppReqDetailDTO appReqDetailDTO, String keyType, String menuKeyNo, User user);
    public String findApprStatus(AppReqDetailDTO appReqDetailDTO, User user);
  
    public List selectApprovalList(AppReqDetailDTO appReqDetailDTO, User user);
    
    // ��ǰ�ǻ� ����  method
    public List selectPartAdjStkTakeDetail(AppReqDetailDTO appReqDetailDTO, User user);
    // ����û������ ���� method
    public List selectPtBuyReqDetail(AppReqDetailDTO appReqDetailDTO, User user);
    // ���Ž�û ǰ���� method
    public List selectPtBuyReqItemList(AppReqDetailDTO appReqDetailDTO, User user);
    
    
}