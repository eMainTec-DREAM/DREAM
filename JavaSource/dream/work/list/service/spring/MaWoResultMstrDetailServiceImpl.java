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
 * 작업결과- 상세 serviceimpl 
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
	    
	    maWoResultMstrDetailDTO.setWoGenType("WORSLT"); //작업결과
	    
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
	    
        //요청번호가 있으면 Req에서 생성한 Work Order, TAWOREQRES 생성 
        if(!"".equals(maWoResultMstrCommonDTO.getWoReqId()))
        {
            MaWoReqResDetailService maWoReqResDetailService = (MaWoReqResDetailService) CommonUtil.getBean("maWoReqResDetailService", loginUser);
            
        	MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        	maWoReqResDetailDTO.setWoReqResId(maWoResultMstrDetailDAO.getNextSequence("SQAWOREQRES_ID"));
        	//maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
        	maWoReqResDetailDTO.setResDate(CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));  //작업결과를 최초에 새운 날짜.
        	maWoReqResDetailDTO.setResStatusId("WRK"); //작업중
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
        	
        	// 요청 상태 체크 후 상태 변경
        	MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", loginUser);
        	maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
            maWoReqDetailService.checkStatus(maWoReqCommonDTO, loginUser);
        }
        
        //설비투자번호가 있으면 TAINVTWORK 생성
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
        
        //TAWOPLAN 입력 
        insertPlanDetail(maWoResultMstrDetailDTO, loginUser);
        
		maWoResultMstrDetailDAO.insertWoequip(maWoResultMstrDetailDTO);
		
		return builder.build();
    }
	
	public ResponseDTO updateDetail(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, User loginUser) throws Exception
    {        
    	ResponseDTO.Builder builder = new ResponseDTO.Builder(MessageUtil.getMessage(loginUser.getLocale(), "MESSAGE", "CMSG057"));
	    
	    //교정작업이 아닌경우 작업시간은 작업자의 작업시간보다 많아야 함
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
        //점검 더미 데이터 수정 
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
		//1. 부품출고와 관련된 업무 실행
				//1-1. 사용한 부품에 대한 출고서[TAPTISSLIST]를 생성
				List woPartList = maWoResultMstrDetailDAO.findWopartList(maWoResultMstrDetailDTO);
				
				User loginUser = maWoResultMstrDetailDTO.getLoginUser();
				
				MaPtIssCommonDTO maPtIssCommonDTO = new MaPtIssCommonDTO();
				
				for(int i=0; i<woPartList.size(); i++)
				{
				    Map woPart = (Map) woPartList.get(i);
				    String wopartId = maWoResultMstrDetailDAO.convertString(woPart.get("wopart_id"));
				    String ptisslistId = maWoResultMstrDetailDAO.convertString(woPart.get("ptisslist_id"));
				    if("".equals(ptisslistId)) {
				    	//1.TAWOPARTS에 단가를 taparts에서 찾아서 update
				    	maWoResultMstrDetailDAO.updatePrice(maWoResultMstrDetailDTO, wopartId);
				    	//2. maWoResultMstrDetailDAO.insertPtIssList에 unit_price,tot_price를 tawoparts에서 읽어서 넣도록 수정.
				    	ptisslistId = maWoResultMstrDetailDAO.getNextSequence("SQAPTISSLIST_ID");
				    	maWoResultMstrDetailDAO.insertPtIssList(maWoResultMstrDetailDTO, wopartId, ptisslistId, loginUser);
				    	maWoResultMstrDetailDAO.linkPtIssList(wopartId, ptisslistId, loginUser);
				        
				    }
					//시리얼에 대한 출고서는 나중에 다시 작업해야 함. 부품이 시리얼 자재인경우 시리얼 출고 데이타도 생성..
					//maWoResultMstrDetailDAO.insertPtissSerialList(maWoResultMstrDetailDTO, wopartId, ptisslistId);
					//만약에 오더확정과 동시에 출고 완료를 처리할 경우 출고완료 기능을 수행.
					if("Y".equals(MwareConfig.getIsOrderConfirmToIssue())){
						//생성된 출고서를 확정시킴.
						maPtIssCommonDTO.setPtisslistId(ptisslistId);
						MaPtIssDetailDTO maPtIssDetailDTO = maPtIssDetailService.findDetail(maPtIssCommonDTO, loginUser);
						if(!"C".equals(maPtIssDetailDTO.getPtissStatus())) {
						    maPtIssDetailService.confirmIssuePart(maPtIssDetailDTO, loginUser);
						}
					}
				}
				
		
		//2. 구성부품과 관련된 업무 실행
				//2-1. 구성자재[TAEQPART] 업데이트. 설비가 여러개일 수도 있기 때문에 하나씩 부위 데이타를 갱신처리.
				String[] eqList = maWoResultMstrDetailDAO.findWoEqList(maWoResultMstrDetailDTO);
				for(String equipId : eqList)
				{
					maWoResultMstrDetailDTO.setEquipId(equipId);
					maWoResultMstrDetailDAO.updateEqPart(maWoResultMstrDetailDTO);
				}
				
		//3. 부품수리와 관련된 업무 실행
				//3-1. 만약에 부품이 수리순환품이면 수리이력의 작성 데이타로 발행
				maWoResultMstrDetailDAO.createPtRepairList(maWoResultMstrDetailDTO);
				//3-2. 사용부품에 수리번호 갱신
				maWoResultMstrDetailDAO.updatePtRepairId(maWoResultMstrDetailDTO);
				
		//4. 작업결과서와 관련된 업무 실행.
				//4-1  작업상태[TAWORKORDER]를 완료상태로 변경
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
		        // 작업결과 비용집계
		        MaWoResultMstrDetailService	maWoResultMstrDetailService = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", maWoResultMstrDetailDTO.getCompNo());
		        maWoResultMstrDetailService.updateWoTotAmt(maWoResultMstrDetailDTO, loginUser);
		        //4-2  모든작업에[TAEQHISTORY] 대해서 작업이력을 생성
		        if("".equals(maWoResultMstrDetailDTO.getWorkTime())) {
		            maWoResultMstrDetailDTO.setWorkTime("0");
		        }
		        
		        maWoResultMstrDetailDTO.setEqHistGenType("WORKORDER");
		        
		        int reusltUpdate = maWoResultMstrDetailDAO.updateEqhistory(maWoResultMstrDetailDTO);
		        if(reusltUpdate == 0)
		        {
		        	maWoResultMstrDetailDAO.insertEqhistory(maWoResultMstrDetailDTO);
		        }
		        //4-3. 이상점검과 관련된 업무 실행
		        	//4-3-1. 이상점검 정상 업데이트
                	//maWoResultMstrDetailDAO.updatePmPointStatusGdWoRslt(maWoResultMstrDetailDTO);
		        
		//5. 작업계획서와 관련된 업무 실행.
		        //5-1. 작업상태[TAWOPLAN]를 완료상태로 변경
		        maWoResultMstrDetailDAO.updateWoPlanStatus(maWoResultMstrDetailDTO,"C");
		        //5-2. 이상점검과 관련된 업무 실행
                    //5-2-1. 이상점검 정상 업데이트
                    //maWoResultMstrDetailDAO.updatePmPointStatusGdWoPlan(maWoResultMstrDetailDTO);
		        
		
		//6. 작업요청과 관련된 업무 실행
				//6-1. 작업요청[TAWOREQRES]으로 만들어진 오더에 대해서 요청및접수 데이타를 갱신..
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
                
		        //6-2. 이상점검과 관련된 업무 실행
                    //6-2-1. 이상점검 정상 업데이트
                    //maWoResultMstrDetailDAO.updatePmPointStatusGdWoReq(maWoResultMstrDetailDTO);
                
                // 작업결과서 - 이상점검
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
                
                //6-2-2. 이상점검항목 처리 
                maBdPointDetailService.checkNgPoint("INS", maWoResultMstrDetailDTO.getWkOrId(), "C", DateUtil.getDate(), loginUser);
                
                //7. 예방점검중에서 분해점검[TAWORKORDER를 통해서 실행되는 점검]과 관련된 업무 실행
				//7-1. 분해점검[TAWONGPOINT] 이상점검 정상 업데이트
				maWoResultMstrDetailDAO.updateNgBdPoint(maWoResultMstrDetailDTO);
				//7-2. 분해점검[TAWOPOINT] 이상점검 정상 업데이트  -> 이쪽으로 이상을 발행한다면 옛날 모듈이므로 체크해 봐야 함.
				maWoResultMstrDetailDAO.updateBdPoint(maWoResultMstrDetailDTO);
				
		//8. 예방작업(스케쥴을 통해서 실행된 작업)과 관련된 업무 실행.
				//8-1. 예방작업[TAPMSCHED]의 실적일자을 갱신
				maWoResultMstrDetailDAO.updatePmSched(maWoResultMstrDetailDTO);
				//8-2. 예방작업중에서 교정작업[PMC]의 경우 교정작업타입이 정기작업[R]이면 차기 스케쥴을 조정해 
				if("PMC".equals(maWoResultMstrDetailDTO.getWoTypeId())){
                    
                    String pmId = maWoResultMstrDetailDAO.findPmId(maWoResultMstrDetailDTO);
                    //기준서가 존재하고 교정작업 교정일자 기준 일정 재조정여부가 Y인 경우에 일정 재조정 한다. 
                    if(!"".equals(pmId) && "Y".equals(MwareConfig.getIsWopmcalibResched())){
                        //TAPMLST에 스케쥴 시작일자를 변경하고 스케쥴을 다시 돌림.
                        maWoResultMstrDetailDAO.updateLastSchDate(maWoResultMstrDetailDTO.getCompNo(), pmId, CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
                        
                        //교정스케쥴을 다시 생성
                        maPmMstrDetailDAO.createPmSchedule(maWoResultMstrDetailDTO.getCompNo(),pmId,maWoResultMstrDetailDTO.getEnterBy() );
                        //교정스케쥴에 따라서 작업서를 발행함.
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
                        
                        //PM ID의 유무와 관계 없이 마지막 교정일과 다음 교정일을 업데이트 
                        maPmMstrDetailDAO.updatePmEquip(maWoResultMstrDetailDTO, maWoResultPmCalDTO, pmId);
                    }    
                    
//              }
                
            }
				//8-3. 예방작업중에서 [PMW]의 경우 config IS_WOPMWORK_RESCHED Y이면 차기 스케쥴을 조정 
				if("PMW".equals(maWoResultMstrDetailDTO.getWoTypeId()))
				{					
					if("Y".equals(MwareConfig.getIsWopmworkResched())){   
						
		                String pmId = maWoResultMstrDetailDAO.findPmId(maWoResultMstrDetailDTO);
		                
		                // 오늘기준 미래 일정을 오늘이나 과거 일자로 작업 확정 시 재스케쥴링이 되지 않는 문제로 기존 plan_init_date 일자 변경을 위해 조회
		                String planInitDate = maWoResultMstrDetailDAO.findPlanInitDate(maWoResultMstrDetailDTO, loginUser, pmId);
		                // TAPMSCHED plan_init_date 일자를 확정일자로 수정
		                maWoResultMstrDetailDAO.updatePlanInitDate(maWoResultMstrDetailDTO, loginUser, pmId, CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
		                
		                if(!"".equals(pmId)){
		                    //TAPMEQUIP에 스케쥴 시작일자를 변경하고 스케쥴을 다시 돌림.
		                    maWoResultMstrDetailDAO.updateLastSchDate(maWoResultMstrDetailDTO.getCompNo(), pmId, CommonUtil.convertDate(maWoResultMstrDetailDTO.getWkorDate()));
		                    
		                    //예방작업스케쥴을 다시 생성
		                    maPmMstrDetailDAO.createPmSchedule(maWoResultMstrDetailDTO.getCompNo(),pmId,maWoResultMstrDetailDTO.getEnterBy() );
		                    //예방작업스케쥴에 따라서 작업서를 발행함.
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
		                    
		                    //PM ID의 유무와 관계 없이 마지막 예방작업일과 다음 예방작업일을 업데이트 
		                    maPmMstrDetailDAO.updatePmEquip(maWoResultMstrDetailDTO, maWoResultPmCalDTO, pmId);
		                    
		                    // 오늘기준 미래 일정을 오늘이나 과거 일자로 작업 확정 시 재스케쥴링이 되지 않는 문제로 
		                    // TAPMSCHED 기존 plan_init_date 일자로 다시 업데이트
			                maWoResultMstrDetailDAO.updatePlanInitDate(maWoResultMstrDetailDTO, loginUser, pmId, CommonUtil.convertDate(planInitDate));
		                    
		                }    	
					}
				}
    
    //9. 고장영향성 평가 또는 재발방지 대책서와 관련된 업무 실행.   
            //대웅제약에 대해서 처리...BM의 경우 이 작업서를 실행한다면 공통모듈일 건데, 다른 타입이라면 대웅제약의 특화모률 임.
            //9. 점검[PMI], 교정[PMC]가  아닐 경우 고장영향성 평가에 데이터 INSERT .. 대웅제약의 경우 TI, PMW도 있는데 이 작업에서 고장영향성 평가가 되어야 하는지 판단이 필요함
            // 이작업은 대웅제약의 확장모듈로 이동.
            //Action에서 실행
    
    //10. 
    
    
    //Serial 자재인 경우 고장수리 테이블 데이터 생성 
    //maWoResultMstrDetailDAO.createSerialPtRepairList(maWoResultMstrDetailDTO);
    
    //In Serial Equipment 를  운영상태(eq_status : R)로 수정   --> In Serial은 출고쪽에서 변경하는걸로..
//  maWoResultMstrDetailDAO.updateInEquip(maWoResultMstrDetailDTO);
    //투입 in_Serial Equipment의 p_equip_id 세팅
    //maWoResultMstrDetailDAO.updatePequip(maWoResultMstrDetailDTO);
    //Out Serial Equipment를 수리대기설비( eq_status :P)로 수정
    //maWoResultMstrDetailDAO.updateOutEquip(maWoResultMstrDetailDTO);
            
            
    //11. 메일링 서비스 Y이면 작업이 완료되었을 때 요청에 의한 작업이면 요청자에게 작업완료 메일을 보냄.
    
//	MaWoResultMstrDetailService	maWoResultMstrDetailService = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", maWoResultMstrDetailDTO.getCompNo());
	
        //11. WO_TYPE 이 BM 인 경우에만 위험성 평가 데이터 넣어주기 (20180904 노정현 수정 --> 설비대상 작업만 영향성평가 생성)
        if("BM".equals(maWoResultMstrDetailDTO.getWoTypeId())){
            if(eqList != null && eqList.length > 0)
                maWoResultMstrDetailDAO.insertWorkFmea(maWoResultMstrDetailDTO);
        }
        
        //요청자에게 메일 전송
        MailUtil.sendMail("WRK10", maWoResultMstrDetailDTO.getWkOrId(), loginUser);
        //작업부서 전체에게 전송
        MailUtil.sendMail("WRK20", maWoResultMstrDetailDTO.getWkOrId(), loginUser);
        
        return 0;
    }
	
	
	public int completeCancelDetail(final MaWoResultMstrDetailDTO maWoResultMstrDetailDTO, MaWoResultPmCalDTO maWoResultPmCalDTO) throws Exception
    {
		
		User loginUser = maWoResultMstrDetailDTO.getLoginUser();
	    if("Y".equals(MwareConfig.getIsOrderConfirmToIssue())){
	        //1. 기존 출고를 출고취소로 변경.
	        //1-1. 기존 출고번호를 가져옴
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
		
		//2. 구성부품과 관련된 업무 실행
		//2-1. 구성자재[TAEQPART] 업데이트. 설비가 여러개일 수도 있기 때문에 하나씩 부위 데이타를 갱신처리.
		String[] eqList = maWoResultMstrDetailDAO.findWoEqList(maWoResultMstrDetailDTO);
		for(String equipId : eqList)
		{
			maWoResultMstrDetailDTO.setEquipId(equipId);
			maWoResultMstrDetailDAO.updateCancelEqPart(maWoResultMstrDetailDTO);
		}
				
		//3. 부품수리와 관련된 업무 실행
		//3-1. 만약에 부품이 수리순환품이면 수리이력의 작성 데이타로 발행
		maWoResultMstrDetailDAO.detetePtRepairList(maWoResultMstrDetailDTO);

		//4. 작업결과서와 관련된 업무 실행.
		//4-1  작업상태[TAWORKORDER]를 완료상태로 변경
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
		
        //4-2  모든작업에[TAEQHISTORY] 대해서 작업이력을 생성
        maWoResultMstrDetailDAO.deleteEqhistory(maWoResultMstrDetailDTO);
        //4-3. 이상점검과 관련된 업무 실행
        	//4-3-1. 이상점검 ( 정상->이상 ) 업데이트
			maWoResultMstrDetailDAO.updatePmPointStatusBdWoRslt(maWoResultMstrDetailDTO, "BD");
    
        
		//5. 작업계획서와 관련된 업무 실행.
        //5-1. 작업상태[TAWOPLAN]를 완료상태로 변경
        maWoResultMstrDetailDAO.updateWoPlanStatus(maWoResultMstrDetailDTO,"P");
        //5-2. 이상점검과 관련된 업무 실행
	        //5-2-1. 이상점검 ( 정상->이상 ) 업데이트
	        maWoResultMstrDetailDAO.updatePmPointStatusBdWoPlan(maWoResultMstrDetailDTO, "BD");
    
        
		//6. 작업요청과 관련된 업무 실행
		//6-1. 작업요청[TAWOREQRES]으로 만들어진 오더에 대해서 요청및접수 데이타를 갱신..
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
	        
        //6-2. 이상점검과 관련된 업무 실행
	        //6-2-1. 이상점검 ( 정상->이상 ) 업데이트
	        maWoResultMstrDetailDAO.updatePmPointStatusBdWoReq(maWoResultMstrDetailDTO,"BD");
    
	        //6-2-2. 이상점검항목 처리 
	        MaBdPointDetailService maBdPointDetailService = (MaBdPointDetailService) CommonUtil.getBean("maBdPointDetailService", loginUser);
            maBdPointDetailService.checkNgPoint("INS", maWoResultMstrDetailDTO.getWkOrId(), "S", DateUtil.getDate(), loginUser);
        
		//7. 예방점검중에서 분해점검[TAWORKORDER를 통해서 실행되는 점검]과 관련된 업무 실행=> 취소하더라도 해당점검은 정상으로 둠.
		//8. 예방작업(스케쥴을 통해서 실행된 작업)과 관련된 업무 실행. => 취소하더라도 해당 실적은 완료로 남겨둔다.
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
     	//DYMOS에서는 작업필수 점검항목을 표시하지않음
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
        //DYMOS에서는 작업필수 점검항목을 표시하지않음
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
        //DYMOS에서는 작업필수 점검항목을 표시하지않음
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
        //DYMOS에서는 작업필수 점검항목을 표시하지않음
//      reportMap.put("STPOINT_LIST", maWoResultMstrDetailDAO.findReportWoStPointDetail(maWoResultMstrDetailDTO));
        
        reportList.add((Map)reportMap);

         return reportList;
    }

	public int findSerialCount(MaWoResultMstrDetailDTO maWoResultMstrDetailDTO) {
		// TODO Auto-generated method stub
		
		int serialPartCnt = maWoResultMstrDetailDAO.checkIsSerialPart(maWoResultMstrDetailDTO);
		
		//Serial Parts가 있다면...Serial 갯수 채크 아니면 그냥 0 리턴 (즉, Serial Parts가 아닌 경우 완료로 통과)
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
    
    //작업계획목록 존재여부 검사
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
