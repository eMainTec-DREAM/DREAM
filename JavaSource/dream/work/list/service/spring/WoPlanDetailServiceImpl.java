package dream.work.list.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.StringUtil;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.service.MaWoReqResDetailService;
import dream.work.list.dao.WoPlanDetailDAO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartListDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.service.MaWoResultCraftListService;
import dream.work.list.service.MaWoResultMstrListService;
import dream.work.list.service.MaWoResultPartListService;
import dream.work.list.service.WoPlanDetailService;

/**
 * �۾���ȹ���- �� serviceimpl 
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanDetailServiceTarget"
 * @spring.txbn id="woPlanDetailService"
 * @spring.property name="woPlanDetailDAO" ref="woPlanDetailDAO"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 */
public class WoPlanDetailServiceImpl implements WoPlanDetailService 
{
    private WoPlanDetailDAO woPlanDetailDAO = null;
    private MaWoReqResDetailService maWoReqResDetailService = null;

	public MaWoReqResDetailService getMaWoReqResDetailService()
    {
        return maWoReqResDetailService;
    }

    public void setMaWoReqResDetailService(
            MaWoReqResDetailService maWoReqResDetailService)
    {
        this.maWoReqResDetailService = maWoReqResDetailService;
    }

    public WoPlanDetailDAO getWoPlanDetailDAO() {
		return woPlanDetailDAO;
	}

	public void setWoPlanDetailDAO(WoPlanDetailDAO woPlanDetailDAO) {
		this.woPlanDetailDAO = woPlanDetailDAO;
	}

	public WoPlanDetailDTO findDetail(WoPlanCommonDTO woPlanCommonDTO, User user)throws Exception
    {
        return woPlanDetailDAO.findDetail(woPlanCommonDTO, user);
    }
	
	public int insertDetail(WoPlanDetailDTO woPlanDetailDTO, WoPlanCommonDTO woPlanCommonDTO, User loginUser) throws Exception
    {        
	    //��û��ȣ�� ������ Req���� ������ Work Order, TAWOREQRES ���� 
        if(!"".equals(woPlanCommonDTO.getWoReqId()))
        {
            MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
            maWoReqResDetailDTO.setWoReqResId(woPlanDetailDAO.getNextSequence("SQAWOREQRES_ID"));
            maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
            maWoReqResDetailDTO.setResStatusId("PLN"); //��ȹ��
            maWoReqResDetailDTO.setDeptId(woPlanDetailDTO.getDeptId());
            maWoReqResDetailDTO.setEmpId(woPlanDetailDTO.getEmpId());
            maWoReqResDetailDTO.setResponse(woPlanDetailDTO.getWkOrDesc());
            maWoReqResDetailDTO.setWkorId(woPlanDetailDTO.getWkOrId());
            maWoReqResDetailDTO.setWoreqresMethod("WO");
            maWoReqResDetailDTO.setWoReqGenType("REQ");
            
            MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
            maWoReqCommonDTO.setWoReqId(woPlanCommonDTO.getWoReqId());
            maWoReqCommonDTO.setCompNo(woPlanCommonDTO.getCompNo());
            
            maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
        }
        
        // ���� �� �۾�����(�۾���ȹ��� -> �۾���ȹ��) ���� ���� �� ����
        woPlanDetailDTO.setWoPlanStatusId("PPP");
        
        return woPlanDetailDAO.insertDetail(woPlanDetailDTO, loginUser);
    }
	
    public void insertWoEquip(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception
    {
        woPlanDetailDAO.insertWoequip(woPlanDetailDTO, user);
    }
    
	public int updateDetail(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception
    {        
		// ���� �� �۾�����(�۾���ȹ ����ݷ� -> �۾���ȹ��) ���� ���� �� ����
        woPlanDetailDTO.setWoPlanStatusId("PPP");
        
        woPlanDetailDAO.updateDetail(woPlanDetailDTO, user);
        
        int equipCnt = Integer.parseInt(woPlanDetailDAO.selectWoequipCnt(woPlanDetailDTO, user));
        if(equipCnt==1){
            woPlanDetailDAO.updateWoequip(woPlanDetailDTO, user);
        }
        return 0;
    }
	public int createPoint(WoPlanDetailDTO woPlanDetailDTO) throws Exception
    {
//		return woPlanDetailDAO.createPoint(woPlanDetailDTO);
		return 0;
    }
	public String checkPoint(WoPlanDetailDTO woPlanDetailDTO,User user) throws Exception
	{
		return woPlanDetailDAO.checkPoint(woPlanDetailDTO,user );
	}
	
	public int completeDetail(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception
	{
		// TAWOPLAN���� TAWORKORDER�� ������ INSERT (1��)
		woPlanDetailDAO.insertWoPlan(woPlanDetailDTO, user);
		
		// TAWOPLANCRAFT���� TAWOCRAFT�� ������ INSERT (������ŭ)
		woPlanDetailDAO.insertWoPlanCraft(woPlanDetailDTO, user);

		// TAWOPLANPARTS���� TAPARTS�� ������ INSERT (������ŭ)
        woPlanDetailDAO.insertWoPlanParts(woPlanDetailDTO, user);
	    
	    // TAWOPLAN������ �۾����¸� �Ϸ�� ���� (1��)
	    woPlanDetailDAO.completeDetail(woPlanDetailDTO, user);
	    
	    // TAWOREQ, TAWOREQRES ���¸� �۾������� ����
	    woPlanDetailDAO.updateReqStatus(woPlanDetailDTO, user);
	    woPlanDetailDAO.updateResStatus(woPlanDetailDTO, user);
	    
	    return 0;
	}

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        if("PPC".equals(appReqDetailDTO.getParentStatus()))
        {
            WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
            woPlanCommonDTO.setWkOrId(appReqDetailDTO.getObjectId());
            woPlanCommonDTO.setCompNo(user.getCompNo());
            WoPlanDetailDTO woPlanDetailDTO = woPlanDetailDAO.findDetail(woPlanCommonDTO, user);
            
            this.completeDetail(woPlanDetailDTO, user);
        }
        else
        {
            woPlanDetailDAO.setStatus(appReqDetailDTO, user);
        }
    }

    @Override
    public String woPlanCheck(WoPlanCommonDTO woPlanCommonDTO, User user) throws Exception
    {
        return woPlanDetailDAO.woPlanCheck(woPlanCommonDTO, user);
    }
    
    public String reverseWoPlan(WoPlanDetailDTO woPlanDetailDTO, User user) throws Exception
	{
    	MaWoResultMstrListService maWoResultMstrListService = (MaWoResultMstrListService)CommonUtil.getBean("maWoResultMstrListService", user);
    	MaWoResultCraftListService maWoResultCraftListService = (MaWoResultCraftListService)CommonUtil.getBean("maWoResultCraftListService", user);
    	MaWoResultPartListService maWoResultPartListService = (MaWoResultPartListService)CommonUtil.getBean("maWoResultPartListService", user);
    	
    	String cntWoResultMstr = maWoResultMstrListService.checkWoResultMstrStatus(woPlanDetailDTO.getWkOrId(), user);
    	
    	if(cntWoResultMstr.equals("0")){
    		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    		MaWoResultCraftListDTO maWoResultCraftListDTO = new MaWoResultCraftListDTO();
    		MaWoResultPartListDTO maWoResultPartListDTO = new MaWoResultPartListDTO();
    		
    		// �۾���ȹ���¸� �۾���ȹ�Ϸ� -> �۾���ȹ�ۼ��� ���� ����
        	woPlanDetailDTO.setWoPlanStatusId("PPP");
    		
    		maWoResultMstrCommonDTO.setWkOrId(woPlanDetailDTO.getWkOrId());
    		// �۾��� ����Ǿ��ִ� �۾��� ����
    		String[] woCrftId = StringUtil.toSingleArray(maWoResultCraftListService.findCraftList(maWoResultMstrCommonDTO, maWoResultCraftListDTO, user), "WOCRAFTID");
    		maWoResultCraftListService.deleteCraftList(woCrftId , user.getCompNo());
    		// �۾��� ����Ǿ��ִ� ��ǰ ����
    		String[] woPartId = StringUtil.toSingleArray(maWoResultPartListService.findPartList(maWoResultMstrCommonDTO, maWoResultPartListDTO, user), "WOPARTID");
    		maWoResultPartListService.deletePartList(woPartId, user.getCompNo());
    		// �۾���� ����
    		maWoResultMstrListService.delWoResultMstr(woPlanDetailDTO.getWkOrId(), user);
    		
    		// TAWOPLAN������ �۾����¸� ��ҷ� ����
    		woPlanDetailDAO.updateDetail(woPlanDetailDTO, user);
    	}
	    return cntWoResultMstr;
	}
}
