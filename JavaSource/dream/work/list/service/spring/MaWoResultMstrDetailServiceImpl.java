package dream.work.list.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import common.bean.MwareConfig;
import common.bean.ResponseDTO;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.MailUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrDetailDTO;
import dream.asset.list.service.MaEqMstrDetailService;
import dream.asset.loc.list.service.MaEqLocDetailService;
import common.util.MessageUtil;
import common.util.StringUtil;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.invt.list.dto.InvtWoRsltDTO;
import dream.invt.list.service.InvtWoRsltService;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.service.MaPtIssDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.service.MaWoReqDetailService;
import dream.req.work.service.MaWoReqResDetailService;
import dream.work.list.dao.MaWoResultMstrDetailDAO;
import dream.work.list.dto.MaWoResultCraftListDTO;
import dream.work.list.dto.MaWoResultFailDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.service.MaWoResultCraftListService;
import dream.work.list.service.MaWoResultMstrDetailService;
import dream.work.list.service.WoPlanDetailService;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoPlanListDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoReqListDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointWoRsltListDTO;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;
import dream.work.rpt.mabdpoint.service.MaBdPointWoPlanListService;
import dream.work.rpt.mabdpoint.service.MaBdPointWoReqListService;
import dream.work.rpt.mabdpoint.service.MaBdPointWoRsltListService;

/**
 * �۾����- �� serviceimpl 
 * @author  kim21017
 * @version $Id: MaWoResultMstrDetailServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultMstrDetailServiceTarget"
 * @spring.txbn id="maWoResultMstrDetailService"
 * @spring.property name="maWoResultMstrDetailDAO" ref="maWoResultMstrDetailDAO"
 * @spring.property name="maPmMstrDetailDAO" ref="maPmMstrDetailDAO"
 * @spring.property name="maDocImgDetailDAO" ref="maDocImgDetailDAO"
 * @spring.property name="maPtIssDetailService" ref="maPtIssDetailService"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaWoResultMstrDetailServiceImpl implements MaWoResultMstrDetailService 
{
    private MaWoResultMstrDetailDAO maWoResultMstrDetailDAO = null;
    private MaDocImgDetailDAO maDocImgDetailDAO = null;
    private MaPmMstrDetailDAO maPmMstrDetailDAO = null;
    private MaPtIssDetailService maPtIssDetailService = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
        
	public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaPtIssDetailService getMaPtIssDetailService() {
		return maPtIssDetailService;
	}

	public void setMaPtIssDetailService(MaPtIssDetailService maPtIssDetailService) {
		this.maPtIssDetailService = maPtIssDetailService;
	}

	public MaPmMstrDetailDAO getMaPmMstrDetailDAO() {
		return maPmMstrDetailDAO;
	}

	public void setMaPmMstrDetailDAO(MaPmMstrDetailDAO maPmMstrDetailDAO) {
		this.maPmMstrDetailDAO = maPmMstrDetailDAO;
	}

	public MaDocImgDetailDAO getMaDocImgDetailDAO() {
		return maDocImgDetailDAO;
	}

	public void setMaDocImgDetailDAO(MaDocImgDetailDAO maDocImgDetailDAO) {
		this.maDocImgDetailDAO = maDocImgDetailDAO;
	}

	public MaWoResultMstrDetailDAO getMaWoResultMstrDetailDAO() {
		return maWoResultMstrDetailDAO;
	}

	public void setMaWoResultMstrDetailDAO(MaWoResultMstrDetailDAO maWoResultMstrDetailDAO) {
		this.maWoResultMstrDetailDAO = maWoResultMstrDetailDAO;
	}

	public MaWoResultMstrDetailDTO findDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)throws Exception
    {
		User loginUser = new User();
		return maWoResultMstrDetailDAO.findDetail(maWoResultMstrCommonDTO, loginUser);
    }
	public MaWoResultFailDetailDTO findFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)throws Exception
    {
		
        return maWoResultMstrDetailDAO.findFailDetail(maWoResultMstrCommonDTO);
    }
	public MaWoResultPmCalDTO findCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)throws Exception
    {
		
        return maWoResultMstrDetailDAO.findCalDetail(maWoResultMstrCommonDTO);
    }
	
	public void insertPlanDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception
	{
	    WoPlanDetailDTO woPlanDetailDTO = new WoPlanDetailDTO();
	    
	    maWoResultMstrDetailDTO.setWoGenType("WORSLT"); //�۾����
	    
	    woPlanDetailDTO.setCompNo(maWoResultMstrDetailDTO.getCompNo());
	    woPlanDetailDTO.setWkOrId(maWoResultMstrDetailDTO.getWkOrId());
	    woPlanDetailDTO.setWoPlanStatusId("PPC");
	    woPlanDetailDTO.setWkOrDesc(maWoResultMstrDetailDTO.getWkOrDesc());
	    woPlanDetailDTO.setWoTypeId(maWoResultMstrDetailDTO.getWoTypeId());
	    woPlanDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
	    woPlanDetailDTO.setPmTypeId(maWoResultMstrDetailDTO.getPmTypeId());
	    woPlanDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
	    woPlanDetailDTO.setStartDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getStartDate()));
	    woPlanDetailDTO.setStartTime(CommonUtil.convertTime(maWoResultMstrDetailDTO.getStartTime()));
	    woPlanDetailDTO.setEndDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getEndDate()));
	    woPlanDetailDTO.setEndTime(CommonUtil.convertTime(maWoResultMstrDetailDTO.getEndTime()));
	    woPlanDetailDTO.setWorkTime(CommonUtil.convertMoney(maWoResultMstrDetailDTO.getWorkTime()));
	    woPlanDetailDTO.setPerform(maWoResultMstrDetailDTO.getPerform());
	    woPlanDetailDTO.setWoNo(maWoResultMstrDetailDTO.getWoNo());
	    woPlanDetailDTO.setVendorId(maWoResultMstrDetailDTO.getVendorId());
	    woPlanDetailDTO.setSelfVendorType(maWoResultMstrDetailDTO.getSelfVendorType());
	    woPlanDetailDTO.setWoGenType(maWoResultMstrDetailDTO.getWoGenType());
	    woPlanDetailDTO.setParentWoId(maWoResultMstrDetailDTO.getParentWoId());
	    woPlanDetailDTO.setShiftTypeId(maWoResultMstrDetailDTO.getShiftTypeId());
	    woPlanDetailDTO.setWkorDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
	    woPlanDetailDTO.setTotAmt(CommonUtil.convertMoney(maWoResultMstrDetailDTO.getTotAmt()));
	    woPlanDetailDTO.setEqLocId(maWoResultMstrDetailDTO.getEqLocId());
	    woPlanDetailDTO.setWkCtrId(maWoResultMstrDetailDTO.getWkCtrId());
	    woPlanDetailDTO.setCourseListId(maWoResultMstrDetailDTO.getCourseListId());
	    woPlanDetailDTO.setVendorName(maWoResultMstrDetailDTO.getVendorName());
	    woPlanDetailDTO.setSubEmpId(maWoResultMstrDetailDTO.getSubEmpId());
	    
	    WoPlanDetailService woPlanDetailService = (WoPlanDetailService)CommonUtil.getBean("woPlanDetailService", loginUser);
	    
	    woPlanDetailService.insertDetail(woPlanDetailDTO, new WoPlanCommonDTO(), loginUser);
	}
	
	
	public ResponseDTO insertDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser) throws Exception
    {        
	    ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(loginUser.getLocale(), "MESSAGE", "CMSG057"));
	    
	    maWoResultMstrDetailDAO.insertDetail(maWoResultMstrDetailDTO, loginUser);
	    
	    builder.addData(maWoResultMstrDetailDTO);
	    
        //��û��ȣ�� ������ Req���� ������ Work Order, TAWOREQRES ���� 
        if(!"".equals(maWoResultMstrCommonDTO.getWoReqId()))
        {
            MaWoReqResDetailService maWoReqResDetailService = (MaWoReqResDetailService) CommonUtil.getBean("maWoReqResDetailService", loginUser);
            
        	MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        	maWoReqResDetailDTO.setWoReqResId(maWoResultMstrDetailDAO.getNextSequence("SQAWOREQRES_ID"));
        	//maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
        	maWoReqResDetailDTO.setResDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));  //�۾������ ���ʿ� ���� ��¥.
        	maWoReqResDetailDTO.setResStatusId("WRK"); //�۾���
        	maWoReqResDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
        	maWoReqResDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
        	maWoReqResDetailDTO.setResponse(maWoResultMstrDetailDTO.getWkOrDesc());
        	maWoReqResDetailDTO.setWoreqresMethod("WO");
        	maWoReqResDetailDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());
        	maWoReqResDetailDTO.setWoReqGenType("REQ");
        	
        	MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
        	maWoReqCommonDTO.setWoReqId(maWoResultMstrCommonDTO.getWoReqId());
        	maWoReqCommonDTO.setCompNo(maWoResultMstrCommonDTO.getCompNo());
        	
        	maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
        	
        	// ��û ���� üũ �� ���� ����
        	MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", loginUser);
        	maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
            maWoReqDetailService.checkStatus(maWoReqCommonDTO, loginUser);
        }
        
        //�������ڹ�ȣ�� ������ TAINVTWORK ����
        if(!"".equals(maWoResultMstrCommonDTO.getInvtlistId()))
        {
            InvtWoRsltService invtWoRsltService = (InvtWoRsltService) CommonUtil.getBean("invtWoRsltService", loginUser);
            
            InvtWoRsltDTO invtWoRsltDTO = new InvtWoRsltDTO();
            invtWoRsltDTO.setInvtworkId(maWoResultMstrDetailDAO.getNextSequence("SQAINVTWORK_ID"));
            invtWoRsltDTO.setInvtlistId(maWoResultMstrCommonDTO.getInvtlistId());
            invtWoRsltDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());
            invtWoRsltDTO.setInvtworkMethod("RSLT");
            invtWoRsltService.insert(invtWoRsltDTO, loginUser);
        }
        
        //TAWOPLAN �Է� 
        insertPlanDetail(maWoResultMstrDetailDTO, loginUser);
        
		maWoResultMstrDetailDAO.insertWoequip(maWoResultMstrDetailDTO);
		
		return builder.build();
    }
	
	public ResponseDTO updateDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception
    {        
    	ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(loginUser.getLocale(), "MESSAGE", "CMSG057"));
	    
	    //�����۾��� �ƴѰ�� �۾��ð��� �۾����� �۾��ð����� ���ƾ� ��
	    if(!"PMC".equals(maWoResultMstrDetailDTO.getWoTypeId())) {
	        String woStartDate = CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getStartDate());
	        String woStartTime = CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getStartTime());
	        String woEndDate = CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getEndDate());
	        String woEndTime = CommonUtil.getRowDateToNum(maWoResultMstrDetailDTO.getEndTime());
	        String woStartDateTime = "".equals(woStartDate)?"":(woStartDate+("".equals(woStartTime)?"0000":woStartTime));
	        String woEndDateTime = "".equals(woEndDate)?"":(woEndDate+("".equals(woEndTime)?"2359":woEndTime));
	        
	        if(!"".equals(woStartDateTime) && !"".equals(woEndDateTime)) {
	            String craftStartDate;
	            String craftEndDate;
	            String craftStartTime;
	            String craftEndTime;
	            String craftStartDateTime;
	            String craftEndDateTime;
	            
	            MaWoResultCraftListService maWoResultCraftListService = (MaWoResultCraftListService) CommonUtil.getBean("maWoResultCraftListService", loginUser);
	            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
	            maWoResultMstrCommonDTO.setWkOrId(maWoResultMstrDetailDTO.getWkOrId());
	            List<Map> craftList = maWoResultCraftListService.findCraftList(maWoResultMstrCommonDTO, new MaWoResultCraftListDTO(), loginUser);
	            
	            for(Map craftMap:craftList) {
	                craftStartDate = CommonUtil.getRowDateToNum(StringUtil.valueOf(craftMap.get("STARTDATE")));
	                craftStartTime = CommonUtil.getRowDateToNum(StringUtil.valueOf(craftMap.get("STARTTIME")));
	                craftEndDate = CommonUtil.getRowDateToNum(StringUtil.valueOf(craftMap.get("ENDDATE")));
	                craftEndTime = CommonUtil.getRowDateToNum(StringUtil.valueOf(craftMap.get("ENDTIME")));
	                craftStartDateTime = "".equals(craftStartDate)?"":(craftStartDate+("".equals(craftStartTime)?"0000":craftStartTime));
	                craftEndDateTime = "".equals(craftEndDate)?"":(craftEndDate+("".equals(craftEndTime)?"2359":craftEndTime));
	                
	                if(!"".equals(craftStartDateTime) && !"".equals(craftEndDateTime)) {
	                    if(!(Long.parseLong(woStartDateTime)<=Long.parseLong(craftStartDateTime) && Long.parseLong(craftEndDateTime)<=Long.parseLong(woEndDateTime))) {
	                        builder
	                        .status(HttpServletResponse.SC_BAD_REQUEST)
	                        .message(MessageUtil.getMessage(loginUser.getLocale(), "MESSAGE", "MSG0111"))
	                        .addData(maWoResultMstrDetailDTO);
	                        return builder.build();
	                    }
	                }
	            }
	        }
	    }
	    
		maWoResultMstrDetailDAO.updateDetail(maWoResultMstrDetailDTO,loginUser);
        //���� ���� ������ ���� 
		int equipCnt = Integer.parseInt(maWoResultMstrDetailDAO.selectWoequipCnt(maWoResultMstrDetailDTO));
		
		if(equipCnt<1){
			maWoResultMstrDetailDAO.insertWoequip(maWoResultMstrDetailDTO);
		}else if(equipCnt==1){
			maWoResultMstrDetailDAO.updateWoequip(maWoResultMstrDetailDTO);
		}
       
		builder.addData(maWoResultMstrDetailDTO);
		
        return builder.build();
    }
	public ResponseDTO updateFailDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,MaWoResultFailDetailDTO maWoResultFailDetailDTO, User user) throws Exception
    {        
	    ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "CMSG057"));
	    
        maWoResultMstrDetailDAO.updateFailDetail(maWoResultMstrCommonDTO, maWoResultFailDetailDTO);
        
        builder.addData(maWoResultFailDetailDTO);
        
        return builder.build();
    }
	public ResponseDTO updateCalDetail(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,MaWoResultPmCalDTO maWoResultPmCalDTO, User user) throws Exception
    {        
	    ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(user.getLocale(), "MESSAGE", "CMSG057"));
	    
	    String[] calibInfo = maWoResultMstrDetailDAO.getNextCalibDate(maWoResultMstrCommonDTO, maWoResultPmCalDTO);
	    
	    if(calibInfo != null && calibInfo.length > 0)
	    {	        
	        maWoResultPmCalDTO.setNextCalibDate(CommonUtil.convertDate(calibInfo[0]));
	        maWoResultPmCalDTO.setPeriodTypeId(calibInfo[1]);
	        maWoResultPmCalDTO.setCalibCycle(calibInfo[2]);
	    }
	    
        maWoResultMstrDetailDAO.updateCalDetail(maWoResultMstrCommonDTO, maWoResultPmCalDTO);
        
        builder
        .addData(maWoResultPmCalDTO)
        .addExt("nextCalibDate", maWoResultPmCalDTO.getNextCalibDate());
        
        return builder.build();
    }
	public int createPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) throws Exception
    {
//		return maWoResultMstrDetailDAO.createPoint(maWoResultMstrDetailDTO);
		return 0;
    }
	public String checkPoint(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO,User user) throws Exception
	{
		return maWoResultMstrDetailDAO.checkPoint(maWoResultMstrDetailDTO,user );
	}
	
	
	public int completeDetail(final MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO) throws Exception
    {
		//1. ��ǰ���� ���õ� ���� ����
				//1-1. ����� ��ǰ�� ���� ���[TAPTISSLIST]�� ����
				List woPartList = maWoResultMstrDetailDAO.findWopartList(maWoResultMstrDetailDTO);
				
				User loginUser = maWoResultMstrDetailDTO.getLoginUser();
				
				MaPtIssCommonDTO maPtIssCommonDTO = new MaPtIssCommonDTO();
				
				for(int i=0; i<woPartList.size(); i++)
				{
				    Map woPart = (Map) woPartList.get(i);
				    String wopartId = maWoResultMstrDetailDAO.convertString(woPart.get("wopart_id"));
				    String ptisslistId = maWoResultMstrDetailDAO.convertString(woPart.get("ptisslist_id"));
				    if("".equals(ptisslistId)) {
				    	//1.TAWOPARTS�� �ܰ��� taparts���� ã�Ƽ� update
				    	maWoResultMstrDetailDAO.updatePrice(maWoResultMstrDetailDTO, wopartId);
				    	//2. maWoResultMstrDetailDAO.insertPtIssList�� unit_price,tot_price�� tawoparts���� �о �ֵ��� ����.
				    	ptisslistId = maWoResultMstrDetailDAO.getNextSequence("SQAPTISSLIST_ID");
				    	maWoResultMstrDetailDAO.insertPtIssList(maWoResultMstrDetailDTO, wopartId, ptisslistId, loginUser);
				    	maWoResultMstrDetailDAO.linkPtIssList(wopartId, ptisslistId, loginUser);
				        
				    }
					//�ø��� ���� ����� ���߿� �ٽ� �۾��ؾ� ��. ��ǰ�� �ø��� �����ΰ�� �ø��� ��� ����Ÿ�� ����..
					//maWoResultMstrDetailDAO.insertPtissSerialList(maWoResultMstrDetailDTO, wopartId, ptisslistId);
					//���࿡ ����Ȯ���� ���ÿ� ��� �ϷḦ ó���� ��� ���Ϸ� ����� ����.
					if("Y".equals(MwareConfig.getIsOrderConfirmToIssue())){
						//������ ����� Ȯ����Ŵ.
						maPtIssCommonDTO.setPtisslistId(ptisslistId);
						MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailService.findDetail(maPtIssCommonDTO, loginUser);
						if(!"C".equals(maPtIssDetailDTO.getPtissStatus())) {
						    maPtIssDetailService.confirmIssuePart(maPtIssDetailDTO, loginUser);
						}
					}
				}
				
		
		//2. ������ǰ�� ���õ� ���� ����
				//2-1. ��������[TAEQPART] ������Ʈ. ���� �������� ���� �ֱ� ������ �ϳ��� ���� ����Ÿ�� ����ó��.
				String[] eqList = maWoResultMstrDetailDAO.findWoEqList(maWoResultMstrDetailDTO);
				for(String equipId : eqList)
				{
					maWoResultMstrDetailDTO.setEquipId(equipId);
					maWoResultMstrDetailDAO.updateEqPart(maWoResultMstrDetailDTO);
				}
				
		//3. ��ǰ������ ���õ� ���� ����
				//3-1. ���࿡ ��ǰ�� ������ȯǰ�̸� �����̷��� �ۼ� ����Ÿ�� ����
				maWoResultMstrDetailDAO.createPtRepairList(maWoResultMstrDetailDTO);
				//3-2. ����ǰ�� ������ȣ ����
				maWoResultMstrDetailDAO.updatePtRepairId(maWoResultMstrDetailDTO);
				
		//4. �۾�������� ���õ� ���� ����.
				//4-1  �۾�����[TAWORKORDER]�� �Ϸ���·� ����
		        maWoResultMstrDetailDAO.completeDetail(maWoResultMstrDetailDTO,"C");
		        
		        if(!"".equals(maWoResultMstrDetailDTO.getChangedEqLocNo()) || !"".equals(maWoResultMstrDetailDTO.getChangedEqStatusNo())) {
		        	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)CommonUtil.getBean("maEqMstrDetailService", maWoResultMstrDetailDTO.getCompNo());
		        	MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService)CommonUtil.getBean("maEqLocDetailService", maWoResultMstrDetailDTO.getCompNo());
		        	
		        	MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
		        	maEqMstrCommonDTO.setEquipId(maWoResultMstrDetailDTO.getEquipId());
		        	
		        	MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailService.findDetail(maEqMstrCommonDTO, loginUser);
		        			        	
		        	if(!"".equals(maWoResultMstrDetailDTO.getChangedEqLocNo()))
		        		maEqMstrDetailDTO.setEqLocId(maEqLocDetailService.findEqLocId(maWoResultMstrDetailDTO.getChangedEqLocNo(), loginUser));
		        	if(!"".equals(maWoResultMstrDetailDTO.getChangedEqStatusNo()))
		        		maEqMstrDetailDTO.setEqStatusId(maWoResultMstrDetailDTO.getChangedEqStatusNo());
		        	
		        	maEqMstrDetailService.updateDetail(maEqMstrDetailDTO, loginUser);
		        }
		        // �۾���� �������
		        MaWoResultMstrDetailService	maWoResultMstrDetailService = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", maWoResultMstrDetailDTO.getCompNo());
		        maWoResultMstrDetailService.updateWoTotAmt(maWoResultMstrDetailDTO, loginUser);
		        //4-2  ����۾���[TAEQHISTORY] ���ؼ� �۾��̷��� ����
		        if("".equals(maWoResultMstrDetailDTO.getWorkTime())) {
		            maWoResultMstrDetailDTO.setWorkTime("0");
		        }
		        
		        maWoResultMstrDetailDTO.setEqHistGenType("WORKORDER");
		        
		        int reusltUpdate = maWoResultMstrDetailDAO.updateEqhistory(maWoResultMstrDetailDTO);
		        if(reusltUpdate == 0)
		        {
		        	maWoResultMstrDetailDAO.insertEqhistory(maWoResultMstrDetailDTO);
		        }
		        //4-3. �̻����˰� ���õ� ���� ����
		        	//4-3-1. �̻����� ���� ������Ʈ
                	//maWoResultMstrDetailDAO.updatePmPointStatusGdWoRslt(maWoResultMstrDetailDTO);
		        
		//5. �۾���ȹ���� ���õ� ���� ����.
		        //5-1. �۾�����[TAWOPLAN]�� �Ϸ���·� ����
		        maWoResultMstrDetailDAO.updateWoPlanStatus(maWoResultMstrDetailDTO,"C");
		        //5-2. �̻����˰� ���õ� ���� ����
                    //5-2-1. �̻����� ���� ������Ʈ
                    //maWoResultMstrDetailDAO.updatePmPointStatusGdWoPlan(maWoResultMstrDetailDTO);
		        
		
		//6. �۾���û�� ���õ� ���� ����
				//6-1. �۾���û[TAWOREQRES]���� ������� ������ ���ؼ� ��û������ ����Ÿ�� ����..
/*		        maWoResultMstrDetailDAO.updateWoResStatus(maWoResultMstrDetailDTO, "COM");
		        maWoResultMstrDetailDAO.updateWoReqStatus(maWoResultMstrDetailDTO);*/
               
                List woReqList = maWoResultMstrDetailDAO.findWoReqId(maWoResultMstrDetailDTO, loginUser);
                for(int i=0; i<woReqList.size(); i++) {
                    MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", loginUser);
                    
                    MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
                    Map woReq = (Map) woReqList.get(i);
                    maWoReqCommonDTO.setWoReqId(woReq.get("woReqId").toString());
                    maWoReqCommonDTO.setWoReqResId(woReq.get("woReqResId").toString());
                                        
                    maWoReqDetailService.checkStatus(maWoReqCommonDTO, loginUser);
                }
                
		        //6-2. �̻����˰� ���õ� ���� ����
                    //6-2-1. �̻����� ���� ������Ʈ
                    //maWoResultMstrDetailDAO.updatePmPointStatusGdWoReq(maWoResultMstrDetailDTO);
                
                // �۾������ - �̻�����
                MaBdPointWoRsltListService maBdPointWoRsltListService = (MaBdPointWoRsltListService)CommonUtil.getBean("maBdPointWoRsltListService", loginUser);
                MaBdPointWoPlanListService maBdPointWoPlanListService = (MaBdPointWoPlanListService)CommonUtil.getBean("maBdPointWoPlanListService", loginUser);
                MaBdPointWoReqListService maBdPointWoReqListService = (MaBdPointWoReqListService)CommonUtil.getBean("maBdPointWoReqListService", loginUser);
                MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService)CommonUtil.getBean("maBdPointDetailService", loginUser);
                
                if(!maWoResultMstrDetailDTO.getWoNgPointId().equals("") && maWoResultMstrDetailDTO.getWoNgPointId() != null){
	                MaBdPointCommonDTO maBdPointCommonDTO = new MaBdPointCommonDTO();
	                maBdPointCommonDTO.setWoNgPointId(maWoResultMstrDetailDTO.getWoNgPointId());
	                MaBdPointWoRsltListDTO maBdPointWoRsltListDTO = new MaBdPointWoRsltListDTO();
	                maBdPointWoRsltListDTO.setWoStatusId("-C");
	                MaBdPointWoPlanListDTO maBdPointWoPlanListDTO = new MaBdPointWoPlanListDTO();
	                maBdPointWoPlanListDTO.setWoStatusId("-C");
	                MaBdPointWoReqListDTO maBdPointWoReqListDTO = new MaBdPointWoReqListDTO();
	                maBdPointWoReqListDTO.setWoReqStatusId("-COM");
	                
	                int woComCnt = Integer.parseInt(maBdPointWoRsltListService.findTotalCount(maBdPointCommonDTO, maBdPointWoRsltListDTO, loginUser));
	                int woPlanComCnt = Integer.parseInt(maBdPointWoPlanListService.findTotalCount(maBdPointCommonDTO, maBdPointWoPlanListDTO, loginUser));
	                int woReqComCnt = Integer.parseInt(maBdPointWoReqListService.findTotalCount(maBdPointCommonDTO, maBdPointWoReqListDTO, loginUser));
	                
	                if(woComCnt + woPlanComCnt + woReqComCnt == 0) {
	                	MaBdPointDetailDTO maBdPointDetailDTO = maBdPointDetailService.findDetail(maBdPointCommonDTO, loginUser);
	                	maBdPointDetailDTO.setPmPointRepStatus("GD");
	                	maBdPointDetailDTO.setCompNo(loginUser.getCompNo());
	                	
	                	maBdPointDetailService.updateDetail(maBdPointDetailDTO);
	                }
                }
                
                //6-2-2. �̻������׸� ó�� 
                maBdPointDetailService.checkNgPoint("INS", maWoResultMstrDetailDTO.getWkOrId(), "C", DateUtil.getDate(), loginUser);
                
                //7. ���������߿��� ��������[TAWORKORDER�� ���ؼ� ����Ǵ� ����]�� ���õ� ���� ����
				//7-1. ��������[TAWONGPOINT] �̻����� ���� ������Ʈ
				maWoResultMstrDetailDAO.updateNgBdPoint(maWoResultMstrDetailDTO);
				//7-2. ��������[TAWOPOINT] �̻����� ���� ������Ʈ  -> �������� �̻��� �����Ѵٸ� ���� ����̹Ƿ� üũ�� ���� ��.
				maWoResultMstrDetailDAO.updateBdPoint(maWoResultMstrDetailDTO);
				
		//8. �����۾�(�������� ���ؼ� ����� �۾�)�� ���õ� ���� ����.
				//8-1. �����۾�[TAPMSCHED]�� ���������� ����
				maWoResultMstrDetailDAO.updatePmSched(maWoResultMstrDetailDTO);
				//8-2. �����۾��߿��� �����۾�[PMC]�� ��� �����۾�Ÿ���� �����۾�[R]�̸� ���� �������� ������ 
				if("PMC".equals(maWoResultMstrDetailDTO.getWoTypeId())){
                    
                    String pmId = maWoResultMstrDetailDAO.findPmId(maWoResultMstrDetailDTO);
                    //���ؼ��� �����ϰ� �����۾� �������� ���� ���� ���������ΰ� Y�� ��쿡 ���� ������ �Ѵ�. 
                    if(!"".equals(pmId) && "Y".equals(MwareConfig.getIsWopmcalibResched())){
                        //TAPMLST�� ������ �������ڸ� �����ϰ� �������� �ٽ� ����.
                        maWoResultMstrDetailDAO.updateLastSchDate(maWoResultMstrDetailDTO.getCompNo(), pmId, CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
                        
                        //������������ �ٽ� ����
                        maPmMstrDetailDAO.createPmSchedule(maWoResultMstrDetailDTO.getCompNo(),pmId,maWoResultMstrDetailDTO.getEnterBy() );
                        //���������쿡 ���� �۾����� ������.
                        maPmMstrDetailDAO.createWorkOrder(maWoResultMstrDetailDTO.getCompNo(), pmId);

                        
                        //Update nextSche TAPMEQUIP
                        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
                        maWoResultMstrCommonDTO.setWkOrId(maWoResultMstrDetailDTO.getWkOrId());
                        maWoResultMstrCommonDTO.setCompNo(maWoResultMstrDetailDTO.getCompNo());
                        maWoResultPmCalDTO.setWkorDate(maWoResultMstrDetailDTO.getWkorDate());
                        
                        String[] calibInfo = maWoResultMstrDetailDAO.getNextCalibDate(maWoResultMstrCommonDTO, maWoResultPmCalDTO);
                        
                        if(calibInfo != null && calibInfo.length > 0)
                        {           
                            maWoResultPmCalDTO.setNextCalibDate(CommonUtil.convertDate(calibInfo[0]));
                            maWoResultPmCalDTO.setPeriodTypeId(calibInfo[1]);
                            maWoResultPmCalDTO.setCalibCycle(calibInfo[2]);
                        }
                        
                        //PM ID�� ������ ���� ���� ������ �����ϰ� ���� �������� ������Ʈ 
                        maPmMstrDetailDAO.updatePmEquip(maWoResultMstrDetailDTO, maWoResultPmCalDTO, pmId);
                    }    
                    
//              }
                
            }
				//8-3. �����۾��߿��� [PMW]�� ��� config IS_WOPMWORK_RESCHED Y�̸� ���� �������� ���� 
				if("PMW".equals(maWoResultMstrDetailDTO.getWoTypeId()))
				{					
					if("Y".equals(MwareConfig.getIsWopmworkResched())){   
						
		                String pmId = maWoResultMstrDetailDAO.findPmId(maWoResultMstrDetailDTO);
		                
		                // ���ñ��� �̷� ������ �����̳� ���� ���ڷ� �۾� Ȯ�� �� �罺���층�� ���� �ʴ� ������ ���� plan_init_date ���� ������ ���� ��ȸ
		                String planInitDate = maWoResultMstrDetailDAO.findPlanInitDate(maWoResultMstrDetailDTO, loginUser, pmId);
		                // TAPMSCHED plan_init_date ���ڸ� Ȯ�����ڷ� ����
		                maWoResultMstrDetailDAO.updatePlanInitDate(maWoResultMstrDetailDTO, loginUser, pmId, CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
		                
		                if(!"".equals(pmId)){
		                    //TAPMEQUIP�� ������ �������ڸ� �����ϰ� �������� �ٽ� ����.
		                    maWoResultMstrDetailDAO.updateLastSchDate(maWoResultMstrDetailDTO.getCompNo(), pmId, CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
		                    
		                    //�����۾��������� �ٽ� ����
		                    maPmMstrDetailDAO.createPmSchedule(maWoResultMstrDetailDTO.getCompNo(),pmId,maWoResultMstrDetailDTO.getEnterBy() );
		                    //�����۾������쿡 ���� �۾����� ������.
		                    maPmMstrDetailDAO.createWorkOrder(maWoResultMstrDetailDTO.getCompNo(), pmId);
		
		                    
		                    //Update nextSche TAPMEQUIP
		                    MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
		                    maWoResultMstrCommonDTO.setWkOrId(maWoResultMstrDetailDTO.getWkOrId());
		                    maWoResultMstrCommonDTO.setCompNo(maWoResultMstrDetailDTO.getCompNo());
		                    maWoResultPmCalDTO.setWkorDate(maWoResultMstrDetailDTO.getWkorDate());
		                    
		                    String[] calibInfo = maWoResultMstrDetailDAO.getNextPmWoDate(maWoResultMstrCommonDTO, maWoResultMstrDetailDTO);
		                    
		                    if(calibInfo != null && calibInfo.length > 0)
		                    {           
		                        maWoResultPmCalDTO.setNextCalibDate(CommonUtil.convertDate(calibInfo[0]));
		                        maWoResultPmCalDTO.setPeriodTypeId(calibInfo[1]);
		                        maWoResultPmCalDTO.setCalibCycle(calibInfo[2]);
		                    }
		                    
		                    //PM ID�� ������ ���� ���� ������ �����۾��ϰ� ���� �����۾����� ������Ʈ 
		                    maPmMstrDetailDAO.updatePmEquip(maWoResultMstrDetailDTO, maWoResultPmCalDTO, pmId);
		                    
		                    // ���ñ��� �̷� ������ �����̳� ���� ���ڷ� �۾� Ȯ�� �� �罺���층�� ���� �ʴ� ������ 
		                    // TAPMSCHED ���� plan_init_date ���ڷ� �ٽ� ������Ʈ
			                maWoResultMstrDetailDAO.updatePlanInitDate(maWoResultMstrDetailDTO, loginUser, pmId, CommonUtil.convertDate(planInitDate));
		                    
		                }    	
					}
				}
    
    //9. ���念�⼺ �� �Ǵ� ��߹��� ��å���� ���õ� ���� ����.   
            //������࿡ ���ؼ� ó��...BM�� ��� �� �۾����� �����Ѵٸ� �������� �ǵ�, �ٸ� Ÿ���̶�� ��������� Ưȭ��� ��.
            //9. ����[PMI], ����[PMC]��  �ƴ� ��� ���念�⼺ �򰡿� ������ INSERT .. ��������� ��� TI, PMW�� �ִµ� �� �۾����� ���念�⼺ �򰡰� �Ǿ�� �ϴ��� �Ǵ��� �ʿ���
            // ���۾��� ��������� Ȯ����� �̵�.
            //Action���� ����
    
    //10. 
    
    
    //Serial ������ ��� ������� ���̺� ������ ���� 
    //maWoResultMstrDetailDAO.createSerialPtRepairList(maWoResultMstrDetailDTO);
    
    //In Serial Equipment ��  �����(eq_status : R)�� ����   --> In Serial�� ����ʿ��� �����ϴ°ɷ�..
//  maWoResultMstrDetailDAO.updateInEquip(maWoResultMstrDetailDTO);
    //���� in_Serial Equipment�� p_equip_id ����
    //maWoResultMstrDetailDAO.updatePequip(maWoResultMstrDetailDTO);
    //Out Serial Equipment�� ������⼳��( eq_status :P)�� ����
    //maWoResultMstrDetailDAO.updateOutEquip(maWoResultMstrDetailDTO);
            
            
    //11. ���ϸ� ���� Y�̸� �۾��� �Ϸ�Ǿ��� �� ��û�� ���� �۾��̸� ��û�ڿ��� �۾��Ϸ� ������ ����.
    
//	MaWoResultMstrDetailService	maWoResultMstrDetailService = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", maWoResultMstrDetailDTO.getCompNo());
	
        //11. WO_TYPE �� BM �� ��쿡�� ���輺 �� ������ �־��ֱ� (20180904 ������ ���� --> ������ �۾��� ���⼺�� ����)
        if("BM".equals(maWoResultMstrDetailDTO.getWoTypeId())){
            if(eqList != null && eqList.length > 0)
                maWoResultMstrDetailDAO.insertWorkFmea(maWoResultMstrDetailDTO);
        }
        
        //��û�ڿ��� ���� ����
        MailUtil.sendMail("WRK10", maWoResultMstrDetailDTO.getWkOrId(), loginUser);
        //�۾��μ� ��ü���� ����
        MailUtil.sendMail("WRK20", maWoResultMstrDetailDTO.getWkOrId(), loginUser);
        
        return 0;
    }
	
	
	public int completeCancelDetail(final MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO) throws Exception
    {
		
		User loginUser = maWoResultMstrDetailDTO.getLoginUser();
	    if("Y".equals(MwareConfig.getIsOrderConfirmToIssue())){
	        //1. ���� ��� �����ҷ� ����.
	        //1-1. ���� ����ȣ�� ������
	        List woPartList = maWoResultMstrDetailDAO.findWopartList(maWoResultMstrDetailDTO);
	        
	        MaPtIssCommonDTO maPtIssCommonDTO = new MaPtIssCommonDTO();
	        
	        for(int i=0; i<woPartList.size(); i++)
	        {
	            Map woPart = (Map) woPartList.get(i);
	            String ptisslistId = maWoResultMstrDetailDAO.convertString(woPart.get("ptisslist_id"));
	            maPtIssCommonDTO.setPtisslistId(ptisslistId);
	            MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailService.findDetail(maPtIssCommonDTO, loginUser);
	            if("C".equals(maPtIssDetailDTO.getPtissStatus())) {
	                maPtIssDetailService.cancelIssuePart(maPtIssDetailDTO, loginUser);
	            }
	        }
	    }
		
		//2. ������ǰ�� ���õ� ���� ����
		//2-1. ��������[TAEQPART] ������Ʈ. ���� �������� ���� �ֱ� ������ �ϳ��� ���� ����Ÿ�� ����ó��.
		String[] eqList = maWoResultMstrDetailDAO.findWoEqList(maWoResultMstrDetailDTO);
		for(String equipId : eqList)
		{
			maWoResultMstrDetailDTO.setEquipId(equipId);
			maWoResultMstrDetailDAO.updateCancelEqPart(maWoResultMstrDetailDTO);
		}
				
		//3. ��ǰ������ ���õ� ���� ����
		//3-1. ���࿡ ��ǰ�� ������ȯǰ�̸� �����̷��� �ۼ� ����Ÿ�� ����
		maWoResultMstrDetailDAO.detetePtRepairList(maWoResultMstrDetailDTO);

		//4. �۾�������� ���õ� ���� ����.
		//4-1  �۾�����[TAWORKORDER]�� �Ϸ���·� ����
		maWoResultMstrDetailDAO.completeDetail(maWoResultMstrDetailDTO,"P");
		
		if(!"".equals(maWoResultMstrDetailDTO.getCurrentEqLocNo()) || !"".equals(maWoResultMstrDetailDTO.getCurrentEqStatusNo())) {
        	MaEqMstrDetailService maEqMstrDetailService = (MaEqMstrDetailService)CommonUtil.getBean("maEqMstrDetailService", maWoResultMstrDetailDTO.getCompNo());
        	MaEqLocDetailService maEqLocDetailService = (MaEqLocDetailService)CommonUtil.getBean("maEqLocDetailService", maWoResultMstrDetailDTO.getCompNo());
        	
        	MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        	maEqMstrCommonDTO.setEquipId(maWoResultMstrDetailDTO.getEquipId());
        	
        	MaEqMstrDetailDTO maEqMstrDetailDTO = maEqMstrDetailService.findDetail(maEqMstrCommonDTO, loginUser);
        			        	
        	if(!"".equals(maWoResultMstrDetailDTO.getCurrentEqLocNo()))
        		maEqMstrDetailDTO.setEqLocId(maEqLocDetailService.findEqLocId(maWoResultMstrDetailDTO.getCurrentEqLocNo(), loginUser));
        	if(!"".equals(maWoResultMstrDetailDTO.getCurrentEqStatusNo()))
        		maEqMstrDetailDTO.setEqStatusId(maWoResultMstrDetailDTO.getCurrentEqStatusNo());
        	
        	maEqMstrDetailService.updateDetail(maEqMstrDetailDTO, loginUser);
        }
		
        //4-2  ����۾���[TAEQHISTORY] ���ؼ� �۾��̷��� ����
        maWoResultMstrDetailDAO.deleteEqhistory(maWoResultMstrDetailDTO);
        //4-3. �̻����˰� ���õ� ���� ����
        	//4-3-1. �̻����� ( ����->�̻� ) ������Ʈ
			maWoResultMstrDetailDAO.updatePmPointStatusBdWoRslt(maWoResultMstrDetailDTO, "BD");
    
        
		//5. �۾���ȹ���� ���õ� ���� ����.
        //5-1. �۾�����[TAWOPLAN]�� �Ϸ���·� ����
        maWoResultMstrDetailDAO.updateWoPlanStatus(maWoResultMstrDetailDTO,"P");
        //5-2. �̻����˰� ���õ� ���� ����
	        //5-2-1. �̻����� ( ����->�̻� ) ������Ʈ
	        maWoResultMstrDetailDAO.updatePmPointStatusBdWoPlan(maWoResultMstrDetailDTO, "BD");
    
        
		//6. �۾���û�� ���õ� ���� ����
		//6-1. �۾���û[TAWOREQRES]���� ������� ������ ���ؼ� ��û������ ����Ÿ�� ����..
        /*maWoResultMstrDetailDAO.updateWoResStatus(maWoResultMstrDetailDTO,"WRK");
        maWoResultMstrDetailDAO.updateWoReqStatus(maWoResultMstrDetailDTO);*/

	        List woReqList = maWoResultMstrDetailDAO.findWoReqId(maWoResultMstrDetailDTO, loginUser);
            
	        for(int i=0; i<woReqList.size(); i++) {
                MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", loginUser);
                
                MaWoReqCommonDTO maWoReqCommonDTO = new MaWoReqCommonDTO();
                Map woReq = (Map) woReqList.get(i);
                maWoReqCommonDTO.setWoReqId(woReq.get("woReqId").toString());
                maWoReqCommonDTO.setWoReqResId(woReq.get("woReqResId").toString());
                                    
                maWoReqDetailService.checkStatus(maWoReqCommonDTO, loginUser);
            }
	        
        //6-2. �̻����˰� ���õ� ���� ����
	        //6-2-1. �̻����� ( ����->�̻� ) ������Ʈ
	        maWoResultMstrDetailDAO.updatePmPointStatusBdWoReq(maWoResultMstrDetailDTO,"BD");
    
	        //6-2-2. �̻������׸� ó�� 
	        MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", loginUser);
            maBdPointDetailService.checkNgPoint("INS", maWoResultMstrDetailDTO.getWkOrId(), "S", DateUtil.getDate(), loginUser);
        
		//7. ���������߿��� ��������[TAWORKORDER�� ���ؼ� ����Ǵ� ����]�� ���õ� ���� ����=> ����ϴ��� �ش������� �������� ��.
		//8. �����۾�(�������� ���ؼ� ����� �۾�)�� ���õ� ���� ����. => ����ϴ��� �ش� ������ �Ϸ�� ���ܵд�.
	        maWoResultMstrDetailDAO.cancelConfirmPmSched(maWoResultMstrDetailDTO, "S");
		
        return 0;
    }
	
	
	public List getReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		Map<String, Object> reportMap = null;
		List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
		
		List detailList = maWoResultMstrDetailDAO.findReportWoDetail(maWoResultMstrDetailDTO);
     	reportMap = (Map)detailList.get(0);
     	reportMap.put("CRAFT_LIST", maWoResultMstrDetailDAO.findReportWoCraftDetail(maWoResultMstrDetailDTO));
     	reportMap.put("PART_LIST", maWoResultMstrDetailDAO.findReportWoPartDetail(maWoResultMstrDetailDTO));
     	reportMap.put("POINT_LIST", maWoResultMstrDetailDAO.findReportWoPointDetail(maWoResultMstrDetailDTO));
     	reportMap.put("EQ_LIST", maWoResultMstrDetailDAO.findReportWoEqDetail(maWoResultMstrDetailDTO));
     	//DYMOS������ �۾��ʼ� �����׸��� ǥ����������
//     	reportMap.put("STPOINT_LIST", maWoResultMstrDetailDAO.findReportWoStPointDetail(maWoResultMstrDetailDTO));
        
     	reportList.add((Map)reportMap);

	     return reportList;
	}
	public List getPmGnlReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
        Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        List detailList = maWoResultMstrDetailDAO.findPmGnlReportWoDetail(maWoResultMstrDetailDTO);
        reportMap = (Map)detailList.get(0);
        reportMap.put("CALIB_STD_EQ_LIST", maWoResultMstrDetailDAO.findReportWoCalibStdEqDetail(maWoResultMstrDetailDTO));
        reportMap.put("CALIB_GNL_VALUE_LIST", maWoResultMstrDetailDAO.findReportWoCalibGnlValueDetail(maWoResultMstrDetailDTO));
        //DYMOS������ �۾��ʼ� �����׸��� ǥ����������
//      reportMap.put("STPOINT_LIST", maWoResultMstrDetailDAO.findReportWoStPointDetail(maWoResultMstrDetailDTO));
        
        reportList.add((Map)reportMap);

         return reportList;
    }
	public List getPmPrsReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
        Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        List detailList = maWoResultMstrDetailDAO.findPmPrsReportWoDetail(maWoResultMstrDetailDTO);
        reportMap = (Map)detailList.get(0);
        reportMap.put("CALIB_STD_EQ_LIST", maWoResultMstrDetailDAO.findReportWoCalibStdEqDetail(maWoResultMstrDetailDTO));
        reportMap.put("CALIB_GNL_VALUE_LIST", maWoResultMstrDetailDAO.findReportWoCalibGnlValueDetail(maWoResultMstrDetailDTO));
        //DYMOS������ �۾��ʼ� �����׸��� ǥ����������
//      reportMap.put("STPOINT_LIST", maWoResultMstrDetailDAO.findReportWoStPointDetail(maWoResultMstrDetailDTO));
        
        reportList.add((Map)reportMap);

         return reportList;
    }
	public List getPmSclReportView(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
        Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        
        List detailList = maWoResultMstrDetailDAO.findPmSclReportWoDetail(maWoResultMstrDetailDTO);
        reportMap = (Map)detailList.get(0);
        reportMap.put("CALIB_STD_EQ_LIST", maWoResultMstrDetailDAO.findReportWoCalibStdEqDetail(maWoResultMstrDetailDTO));
        List calibSclValueList = maWoResultMstrDetailDAO.findReportWoCalibSclValueDetail(maWoResultMstrDetailDTO);
        reportMap.putAll((Map)calibSclValueList.get(0));
        //DYMOS������ �۾��ʼ� �����׸��� ǥ����������
//      reportMap.put("STPOINT_LIST", maWoResultMstrDetailDAO.findReportWoStPointDetail(maWoResultMstrDetailDTO));
        
        reportList.add((Map)reportMap);

         return reportList;
    }

	public int findSerialCount(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		// TODO Auto-generated method stub
		
		int serialPartCnt = maWoResultMstrDetailDAO.checkIsSerialPart(maWoResultMstrDetailDTO);
		
		//Serial Parts�� �ִٸ�...Serial ���� äũ �ƴϸ� �׳� 0 ���� (��, Serial Parts�� �ƴ� ��� �Ϸ�� ���)
		if(serialPartCnt > 0)
			return maWoResultMstrDetailDAO.findSerialCount(maWoResultMstrDetailDTO);
		else return 0;
	}

    @Override
    public String getStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        return maWoResultMstrDetailDAO.getStatus(appReqDetailDTO, user);
    }

    @Override
    public void appProcess(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
        maWoResultMstrDetailDAO.setStatus(appReqDetailDTO, user);
        
        if("C".equals(appReqDetailDTO.getParentStatus()))
        {
            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
            maWoResultMstrCommonDTO.setWkOrId(appReqDetailDTO.getObjectId());
            maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
            maWoResultMstrCommonDTO.setUserLang(user.getLangId());
            
            MaWoResultMstrDetailDTO maWoResultMstrDetailDTO =  findDetail(maWoResultMstrCommonDTO);
            MaWoResultPmCalDTO maWoResultPmCalDTO = findCalDetail(maWoResultMstrCommonDTO);
            
            maWoResultMstrDetailDTO.setEnterBy(user.getUserId());
            maWoResultMstrDetailDTO.setCompNo(user.getCompNo());
            maWoResultMstrDetailDTO.setLoginUser(user);

            completeDetail(maWoResultMstrDetailDTO, maWoResultPmCalDTO);
        }
    } 

    public String getLastAppEmpId(AppReqDetailDTO appReqDetailDTO, User user) throws Exception
    {
    	return maWoResultMstrDetailDAO.getLastAppEmpId(appReqDetailDTO, user);
    }

    @Override
    public String findPage(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user)
    {
        return maWoResultMstrDetailDAO.findPage(maWoResultMstrCommonDTO, user);
    }
    
    //�۾���ȹ��� ���翩�� �˻�
    public String woPlanCheck(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user) throws Exception
    {
        return maWoResultMstrDetailDAO.woPlanCheck(maWoResultMstrCommonDTO,user );
    }

	@Override
	public int updateWoStatus(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception {
/*		if("PRW".equals(maWoResultMstrDetailDTO.getWoStatusId()) || "P".equals(maWoResultMstrDetailDTO.getWoStatusId())
				|| "PRWDA".equals(maWoResultMstrDetailDTO.getWoStatusId())){
			maWoResultMstrDetailDTO.setWoStatusId("PRP");
		} else if ("PRP".equals(maWoResultMstrDetailDTO.getWoStatusId())) {
			maWoResultMstrDetailDTO.setWoStatusId("P");
		}*/
		 return maWoResultMstrDetailDAO.updateWoStatus(maWoResultMstrDetailDTO,loginUser);
	}

	@Override
	public int updateWoTotAmt(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception
	{
		return maWoResultMstrDetailDAO.updateWoTotAmt(maWoResultMstrDetailDTO, loginUser);
	}
}
