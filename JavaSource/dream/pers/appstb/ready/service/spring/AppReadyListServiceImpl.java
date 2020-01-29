package dream.pers.appstb.ready.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.comm.revision.service.CommRevService;
import dream.invt.list.service.InvtDetailService;
import dream.invt.list.service.InvtPrcDetailService;
import dream.note.daily.service.MaWoDailyDetailService;
import dream.part.adj.stktake.service.PartAdjStkTakeDetailService;
import dream.part.iss.emg.req.service.PartIssEmgReqDetailService;
import dream.part.pur.buy.service.MaPtBuyReqHdrDetailService;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.message.mail.service.PersAppstbMessageMailService;
import dream.pers.appstb.ready.dao.AppReadyListDAO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;
import dream.pers.appstb.ready.service.AppReadyListService;
import dream.req.work.service.ReqWorkDetailService;
import dream.work.cal.pminsappr.service.WorkCalPmInsApprDetailService;
import dream.work.cal.pmyear.service.MaWoYearSchedListService;
import dream.work.fmea.list.service.WorkFmeaDetailService;
import dream.work.list.service.MaWoResultMstrDetailService;
import dream.work.list.service.WoPlanDetailService;
import dream.work.planappr.service.WorkPlanApprDetailService;
import dream.work.pm.list.service.MaPmMstrDetailService;
import dream.work.pmi.list.service.WorkPmiDetailService;

/**
 * 목록
 * @author javaworker
 * @version $Id: AppReadyListServiceImpl.java,v 1.1 2013/08/30 09:12:10 javaworker Exp $
 * @since 1.0
 * 
 * @spring.bean id="appReadyListServiceTarget"
 * @spring.txbn id="appReadyListService"
 * @spring.property name="appReadyListDAO" ref="appReadyListDAO"
 * @spring.property name="maWoDailyDetailService" ref="maWoDailyDetailService"
 * @spring.property name="invtPrcDetailService" ref="invtPrcDetailService"
 * @spring.property name="invtDetailService" ref="invtDetailService"
 * @spring.property name="reqWorkDetailService" ref="reqWorkDetailService"
 * @spring.property name="maPtBuyReqHdrDetailService" ref="maPtBuyReqHdrDetailService"
 * @spring.property name="partAdjStkTakeDetailService" ref="partAdjStkTakeDetailService"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="woPlanDetailService" ref="woPlanDetailService"
 * @spring.property name="workFmeaDetailService" ref="workFmeaDetailService"
 * @spring.property name="persAppstbMessageMailService" ref="persAppstbMessageMailService"
 * @spring.property name="partIssEmgReqDetailService" ref="partIssEmgReqDetailService"
 * @spring.property name="workCalPmInsApprDetailService" ref="workCalPmInsApprDetailService"
 * @spring.property name="workPlanApprDetailService" ref="workPlanApprDetailService"
 * @spring.property name="commRevService" ref="commRevService"
 * @spring.property name="workPmiDetailService" ref="workPmiDetailService"
 * @spring.property name="maPmMstrDetailService" ref="maPmMstrDetailService"
 */
public class AppReadyListServiceImpl implements AppReadyListService
{
    /** 목록DAO */
    private AppReadyListDAO appReadyListDAO = null;
    
    /** 일일작업일지확인 */
    private MaWoDailyDetailService maWoDailyDetailService = null;
    /** 투자진행확인 */
    private InvtPrcDetailService invtPrcDetailService = null;
    /** 투자목록확인 */
    private InvtDetailService invtDetailService = null;
    /** 작업요청확인 */
    private ReqWorkDetailService reqWorkDetailService = null;
    /** 구매신청확인 */
    private MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = null;
    /** 부품실사확인 */
    private PartAdjStkTakeDetailService partAdjStkTakeDetailService = null;
    /** 작업오더확인 */
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    /** 작업계획확인 */
    private WoPlanDetailService woPlanDetailService = null;
    /** 고장영향성평가확인 */
    private WorkFmeaDetailService workFmeaDetailService = null;
    /** 결재시에 메일링 서비스*/
    private PersAppstbMessageMailService persAppstbMessageMailService = null;
    /** 출고요청확인 */
    private PartIssEmgReqDetailService partIssEmgReqDetailService = null;
    /** 예방점검계획승인 */
    private WorkCalPmInsApprDetailService workCalPmInsApprDetailService = null;
    /** 작업계획승인 */
    private WorkPlanApprDetailService workPlanApprDetailService = null;
    /** 재개정 */
    private CommRevService commRevService = null;
    /** 점검실시 */
    private WorkPmiDetailService workPmiDetailService = null;
    /** 점검주기설정 */
    private MaPmMstrDetailService maPmMstrDetailService = null;
    
    public MaPmMstrDetailService getMaPmMstrDetailService() {
		return maPmMstrDetailService;
	}

	public void setMaPmMstrDetailService(MaPmMstrDetailService maPmMstrDetailService) {
		this.maPmMstrDetailService = maPmMstrDetailService;
	}

	public WorkPmiDetailService getWorkPmiDetailService() {
		return workPmiDetailService;
	}

	public void setWorkPmiDetailService(WorkPmiDetailService workPmiDetailService) {
		this.workPmiDetailService = workPmiDetailService;
	}

	public CommRevService getCommRevService()
    {
        return commRevService;
    }

    public void setCommRevService(CommRevService commRevService)
    {
        this.commRevService = commRevService;
    }

    public WorkPlanApprDetailService getWorkPlanApprDetailService() {
		return workPlanApprDetailService;
	}

	public void setWorkPlanApprDetailService(WorkPlanApprDetailService workPlanApprDetailService) {
		this.workPlanApprDetailService = workPlanApprDetailService;
	}

	public WorkCalPmInsApprDetailService getWorkCalPmInsApprDetailService() {
		return workCalPmInsApprDetailService;
	}

	public void setWorkCalPmInsApprDetailService(WorkCalPmInsApprDetailService workCalPmInsApprDetailService) {
		this.workCalPmInsApprDetailService = workCalPmInsApprDetailService;
	}

	public WoPlanDetailService getWoPlanDetailService()
    {
        return woPlanDetailService;
    }

    public void setWoPlanDetailService(WoPlanDetailService woPlanDetailService)
    {
        this.woPlanDetailService = woPlanDetailService;
    }

    public PartIssEmgReqDetailService getPartIssEmgReqDetailService()
    {
        return partIssEmgReqDetailService;
    }

    public void setPartIssEmgReqDetailService(
            PartIssEmgReqDetailService partIssEmgReqDetailService)
    {
        this.partIssEmgReqDetailService = partIssEmgReqDetailService;
    }

    public PersAppstbMessageMailService getPersAppstbMessageMailService() {
		return persAppstbMessageMailService;
	}

	public void setPersAppstbMessageMailService(
			PersAppstbMessageMailService persAppstbMessageMailService) {
		this.persAppstbMessageMailService = persAppstbMessageMailService;
	}

	public WorkFmeaDetailService getWorkFmeaDetailService()
    {
        return workFmeaDetailService;
    }

    public void setWorkFmeaDetailService(
            WorkFmeaDetailService workFmeaDetailService)
    {
        this.workFmeaDetailService = workFmeaDetailService;
    }

    public MaWoResultMstrDetailService getMaWoResultMstrDetailService()
    {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(
            MaWoResultMstrDetailService maWoResultMstrDetailService)
    {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }

    public PartAdjStkTakeDetailService getPartAdjStkTakeDetailService()
    {
        return partAdjStkTakeDetailService;
    }

    public void setPartAdjStkTakeDetailService(
            PartAdjStkTakeDetailService partAdjStkTakeDetailService)
    {
        this.partAdjStkTakeDetailService = partAdjStkTakeDetailService;
    }

    public MaPtBuyReqHdrDetailService getMaPtBuyReqHdrDetailService()
    {
        return maPtBuyReqHdrDetailService;
    }

    public void setMaPtBuyReqHdrDetailService(
            MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService)
    {
        this.maPtBuyReqHdrDetailService = maPtBuyReqHdrDetailService;
    }

    public ReqWorkDetailService getReqWorkDetailService()
    {
        return reqWorkDetailService;
    }

    public void setReqWorkDetailService(ReqWorkDetailService reqWorkDetailService)
    {
        this.reqWorkDetailService = reqWorkDetailService;
    }

    public InvtDetailService getInvtDetailService()
    {
        return invtDetailService;
    }

    public void setInvtDetailService(InvtDetailService invtDetailService)
    {
        this.invtDetailService = invtDetailService;
    }

    public InvtPrcDetailService getInvtPrcDetailService()
    {
        return invtPrcDetailService;
    }

    public void setInvtPrcDetailService(InvtPrcDetailService invtPrcDetailService)
    {
        this.invtPrcDetailService = invtPrcDetailService;
    }

    public MaWoDailyDetailService getMaWoDailyDetailService()
    {
        return maWoDailyDetailService;
    }

    public void setMaWoDailyDetailService(
            MaWoDailyDetailService maWoDailyDetailService)
    {
        this.maWoDailyDetailService = maWoDailyDetailService;
    }

    public AppReadyListDAO getAppReadyListDAO()
    {
        return appReadyListDAO;
    }

    public void setAppReadyListDAO(AppReadyListDAO appReadyListDAO)
    {
        this.appReadyListDAO = appReadyListDAO;
    }
    
    public List findAppReadyList(AppReadyCommonDTO appReadyCommonDTO, User user)
    {
        return appReadyListDAO.findList(appReadyCommonDTO, user);
    }

    public int deleteLine(String[] deleteRows, String compNo)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + appReadyListDAO.deleteLine(id,compNo);
            }
        return result;
    }

    
    public int approveLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception
    {
        int result = 0;
        String apprUserType = "";
        if(!apprusrIds.equals(null))
        {
            for(String apprusrId : apprusrIds)
            {
            	//선택한 데이타가 결재인지 확인하고 결재일 경우만 처리. 참조확인과 반려는 처리 하지 않음.[AP:결재자   RF:참조자  DF:기안자]
            	apprUserType =  appReadyListDAO.checkApprUsrType(apprusrId, user);

            	if("AP".equals(apprUserType)){ 
            		//apprusr_action : [P:처리대상 -> C:처리완료] 변경
                    //apprusr_status : [Z:대기  -> C:승인 ] 변경
            		appReadyListDAO.approveLine(apprusrId,  user, "C", "C", appReadyCommonDTO);
            		
            		//0.자신과 동일한 레벨에 아직 결재하지 않는 대기자가 존재하지 않아야 처리하고 병렬결재자가 존재한다면 skip한다.
            		if("0".equals(appReadyListDAO.isParalApprUser(apprusrId, user))){
            			
            			//1. 다음결재자이전에 참조자가 있다면 이 참조자는 모두 대기 상태로 변경처리.
            			appReadyListDAO.updateReferenceLine(apprusrId, user);
            			
            			//2.다음결재자를 결재대기 로 갱신처리.
            			int rstCnt = appReadyListDAO.updateApprovalLine(apprusrId, user);
            			
            			//3. 다음결재자가 없다면 최종결재완료[C] 상태임.  다음결재자가 있다면 아직 결재 진행중[P]임.
            			if(rstCnt == 0){
            				appReadyListDAO.updateApproveList(apprusrId,  user, "C");
            			}else{
            				appReadyListDAO.updateApproveList(apprusrId,  user, "P");
            			}
            			
            		}
            		
            		//4. 결재상태에 따라서 원본 상태도 변경처리함.
            		AppReqDetailDTO appReqDetailDTO = appReadyListDAO.findListDetail(apprusrId, user);
                    apprProcess(appReqDetailDTO, user);
                    
                    
            	}
            }
        }
        
        return result;
        
        
    }
    
    
    public int referenceLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception
    {
    	
    	int result = 0;
        String apprUserType = "";
        if(!apprusrIds.equals(null))
        {
            for(String apprusrId : apprusrIds)
            {
            	//선택한 데이타가 결재인지 확인하고 참조일 경우만 처리. 결재,반려시에는 처리 하지 않음.[AP:결재자   RF:참조자  DF:기안자]
            	apprUserType =  appReadyListDAO.checkApprUsrType(apprusrId, user);

            	if("RF".equals(apprUserType)){ 
            		//apprusr_action : [P:처리대상 -> C:처리완료] 변경
                    //apprusr_status : [Z:대기  -> R:확인 ] 변경
            		appReadyListDAO.approveLine(apprusrId,  user, "C", "R", appReadyCommonDTO);
            		
            	}
            }
        }
    	
    	return result;
    	
    	
    }

    public int rejectLine(String[] apprusrIds, String[] apprlistIds, User user, AppReadyCommonDTO appReadyCommonDTO) throws Exception
    {
        int result = 0;
        String apprUserType = "";
        if(!apprusrIds.equals(null))
        {
            for(String apprusrId : apprusrIds)
            {
                
            	//선택한 데이타가 결재인지 확인하고 참조일 경우만 처리. 결재,반려시에는 처리 하지 않음.[AP:결재자   RF:참조자  DF:기안자]
            	apprUserType =  appReadyListDAO.checkApprUsrType(apprusrId, user);
            	if("AP".equals(apprUserType)){ 
            		//apprusr_action : [P:처리대상 -> C:처리완료] 변경
                    //apprusr_status : [Z:대기  -> D:반려 ] 변경
            		appReadyListDAO.approveLine(apprusrId,  user, "C", "D", appReadyCommonDTO);
            		
            		//1. 현재 결재대기이거나 결재가 진행중인 모든 문서에 대해서 반려처리를 진행함. 반려이면 현재진행중인 데이타는 완료되며 아직 결재가 진행되지 않는 대기중인 결재는 모두 삭제 처리됨.
            		appReadyListDAO.updateRejectList(apprusrId, user);
            		
            		appReadyListDAO.updateApproveList(apprusrId,  user, "D");
            		
            		//2. 결재상태에 따라서 원본 상태도 변경처리함.
            		AppReqDetailDTO appReqDetailDTO = appReadyListDAO.findListDetail(apprusrId, user);
                    apprProcess(appReqDetailDTO, user);
                    
            	}
            }
           
        }
        return result;
    }
    
    
    
    
    
    
    
    public void apprProcess(final AppReqDetailDTO appReqDetailDTO, final User user) throws Exception
    {
        String apprType = appReqDetailDTO.getApprType();
        String apprStatus = appReqDetailDTO.getApprStatus(); //W:작성중, R:결재요청, P:결재진행, C:결재완료, D:결재반려
        String parentStatus = "";
        
        String jsonParam = appReqDetailDTO.getJsonParam();

        if("WODAY".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WODAYLIST_STATUS
            {
                case "W":
                    parentStatus = "";      //작성중 -> 이런경우는 나오지 않음.
                    break;
                case "R":
                    parentStatus = "WRA";      //결재요청 -> 이런경우는 나오지 않음.
                    break;
                case "P":   
                    parentStatus = "WOA";      //결재진행
                    break;
                case "C":
                    parentStatus = "C";      //결재완료
                    break;
                case "D":
                    parentStatus = "WDA";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            maWoDailyDetailService.appProcess(appReqDetailDTO, user);
        }
        if("INVTPRC".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> INVTPHASE_STATUS
            {
                case "W":
                    parentStatus = "";      //대기
                    break;
                case "R":
                    parentStatus = "RA";      //결재요청
                    break;
                case "P":   
                    parentStatus = "OA";      //결재중
                    break;
                case "C":
                    parentStatus = "C";      //완료
                    break;
                case "D":
                    parentStatus = "DA";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            invtPrcDetailService.appProcess(appReqDetailDTO, user);
        }
        
        if("INVTLIST".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> INVTLIST_STATUS
            {
                case "W":
                    parentStatus = "";      //작성
                    break;
                case "R":
                    parentStatus = "RA";      //결재요청
                    break;
                case "P":   
                    parentStatus = "OA";      //결재중
                    break;
                case "C":
                    parentStatus = "P";      //진행
                    break;
                case "D":
                    parentStatus = "DA";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            InvtDetailService  invtDservice = (InvtDetailService) CommonUtil.getBean("invtDetailService", user);
            invtDservice.appProcess(appReqDetailDTO, user);
        }
        if("REQWORK".equals(apprType)) //작업요청..
        {
            switch(apprStatus)              //APPR_STATUS -> WOREQ_STATUS
            {
                case "W":
                    parentStatus = "";      //작성
                    break;
                case "R":
                    parentStatus = "WRTRA";      //요청서 결재요청
                    break;
                case "P":   
                    parentStatus = "WRTOA";      //요청서 결재중
                    break;
                case "C":
                    parentStatus = "REQ";      //요청
                    break;
                case "D":
                    parentStatus = "WRTDA";      //요청서 결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            reqWorkDetailService.appProcess(appReqDetailDTO, user);
        }
        
        if("REQINVTWORK".equals(apprType)) //투자요청..
        {
        	switch(apprStatus)              //APPR_STATUS -> WOREQ_STATUS
        	{
        	case "W":
        		parentStatus = "";      //작성
        		break;
        	case "R":
        		parentStatus = "WRTRA";      //요청서 결재요청
        		break;
        	case "P":   
        		parentStatus = "WRTOA";      //요청서 결재중
        		break;
        	case "C":
        		parentStatus = "REQ";      //요청
        		break;
        	case "D":
        		parentStatus = "WRTDA";      //요청서 결재반려
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);
        	reqWorkDetailService.appProcess(appReqDetailDTO, user);
        }
        
        //구매신청 
        if("PTBUYREQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> PTPRLIST_STATUS
            {
                case "W":
                    parentStatus = "";      //작성
                    break;
                case "R":
                    parentStatus = "WRA";      //결재요청
                    break;
                case "P":   
                    parentStatus = "WOA";      //결재중
                    break;
                case "C":
                    parentStatus = "C";      //요청완료
                    break;
                case "D":
                    parentStatus = "WDA";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            maPtBuyReqHdrDetailService.appProcess(appReqDetailDTO, user);
        }
        if("PTSTKTAKE".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> PTSTKTAKE_STATUS
            {
                case "W":
                    parentStatus = "";      //작성
                    break;
                case "R":
                    parentStatus = "WRA";      //결재요청
                    break;
                case "P":   
                    parentStatus = "WOA";      //결재중
                    break;
                case "C":
                    parentStatus = "C";      //실사확정
                    break;
                case "D":
                    parentStatus = "WDA";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            partAdjStkTakeDetailService.appProcess(appReqDetailDTO, user);
        }
        if("WORKORDER".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WO_STATUS
            {
                case "W":
                    parentStatus = "";      //작성
                    break;
                case "R":
                    parentStatus = "PRWRA";      //결과 결재요청
                    break;
                case "P":   
                    parentStatus = "PRWOA";      //결과 결재중
                    break;
                case "C":
                    parentStatus = "C";             //작업완료
                    break;
                case "D":
                    parentStatus = "PRWDA";      //결과 결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            MaWoResultMstrDetailService wrms = (MaWoResultMstrDetailService)CommonUtil.getBean("maWoResultMstrDetailService", user);
            MaWoYearSchedListService wyss = (MaWoYearSchedListService)CommonUtil.getBean("maWoYearSchedListService", user);
            wrms.appProcess(appReqDetailDTO, user);
            wyss.apprProcess(appReqDetailDTO, user);
        }
        
        if("WOPLAN".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WO_STATUS
            {
                case "W":
                    parentStatus = "";      	 //작성
                    break;
                case "R":
                    parentStatus = "PPWRA";      //작업계획 결재요청
                    break;
                case "P":   
                    parentStatus = "PPWOA";      //작업계획 결재중
                    break;
                case "C":
                    parentStatus = "PPC";        //작업계획완료
                    break;
                case "D":
                    parentStatus = "PPWDA";      //작업계획 결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            woPlanDetailService.appProcess(appReqDetailDTO, user);
        }
        
        if("WORKFMEA".equals(apprType))
        {
            String fmeaStatus = workFmeaDetailService.getStatus(appReqDetailDTO, user);
            if(/**"WRT".equals(fmeaStatus) || */"WRTRA".equals(fmeaStatus) || "WRTOA".equals(fmeaStatus) || "WRTDA".equals(fmeaStatus)) {
                switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
                {
                    case "W":
                        parentStatus = "";      //작성
                        break;
                    case "R":
                        parentStatus = "WRTRA";      //작성 결재요청
                        break;
                    case "P":   
                        parentStatus = "WRTOA";      //작성 결재중
                        break;
                    case "C":
                        parentStatus = "CCA";       //검토중:RCA
                        break;
                    case "D":
                        parentStatus = "WRTDA";      //작성 결재반려
                        break;
                    default:
                        parentStatus = apprStatus;
                        break;
                }
            }
            else {
                switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
                {
                    case "W":
                        parentStatus = "";      //작성
                        break;
                    case "R":
                        parentStatus = "RCARA";      //검토 결재요청
                        break;
                    case "P":   
                        parentStatus = "RCAOA";      //검토 결재중
                        break;
                    case "C":
                        parentStatus = "CCA";             //검토완료
                        break;
                    case "D":
                        parentStatus = "RCADA";      //검토 결재반려
                        break;
                    default:
                        parentStatus = apprStatus;
                        break;
                }
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            WorkFmeaDetailService wrms = (WorkFmeaDetailService)CommonUtil.getBean("workFmeaDetailService", user);
            wrms.appProcess(appReqDetailDTO, user);
        }
        
        if("PTISSREQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
            {
                case "W":
                    parentStatus = "";      //작성중
                    break;
                case "R":
                    parentStatus = "RA";      //결재요청
                    break;
                case "P":   
                    parentStatus = "OA";      //결재중
                    break;
                case "C":
                    parentStatus = "SI";       //출고대기
                    break;
                case "D":
                    parentStatus = "DA";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            partIssEmgReqDetailService.appProcess(appReqDetailDTO, user);
        }
        //예방점검계획승인
        if("PMINSSCHED".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
        	{
        	case "W":
        		parentStatus = "";      //작성중
        		break;
        	case "R":
        		parentStatus = "RA";      //결재요청
        		break;
        	case "P":   
        		parentStatus = "OA";      //결재중
        		break;
        	case "D":
        		parentStatus = "DA";      //결재반려
        		break;
        	case "C":
        		parentStatus = "C";       //결재완료
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	
        	WorkCalPmInsApprDetailService workCalPmInsApprDetailService2 = (WorkCalPmInsApprDetailService)CommonUtil.getBean("workCalPmInsApprDetailService", user);
        	appReqDetailDTO.setParentStatus(parentStatus);
        	workCalPmInsApprDetailService2.appProcess(appReqDetailDTO, user);
        }
        
        //작업계획승인
        if("WOPLANAPPR".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> WOPLANAPPR_STATUS
        	{
        	case "W":
                parentStatus = "";      	 //작성
                break;
            case "R":
                parentStatus = "PPWRA";      //작업계획 결재요청
                break;
            case "P":   
                parentStatus = "PPWOA";      //작업계획 결재중
                break;
            case "C":
                parentStatus = "PPC";        //작업계획완료
                break;
            case "D":
                parentStatus = "PPWDA";      //작업계획 결재반려
                break;
            default:
                parentStatus = apprStatus;
                break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);
        	workPlanApprDetailService.appProcess(appReqDetailDTO, user);
        }
        
        //설비마스터 제개정 완료
        if("EQUIPREV".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> REVISION_STATUS
        	{
        	case "W":
        		parentStatus = "W";      //작성중
        		break;
        	case "R":
        		parentStatus = "RA";      //결재요청
        		break;
        	case "P":   
        		parentStatus = "PA";      //결재중
        		break;
        	case "C":
        		parentStatus = "C";       //결재완료
        		break;
        	case "D":
        		parentStatus = "DA";      //결재반려
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);    
        	CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
        	crs.appProcess(appReqDetailDTO, user, parentStatus, "ASSET");
        }
        
        //계측기마스터 제개정 완료
        if("EQTLREV".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> REVISION_STATUS
        	{
        	case "W":
        		parentStatus = "W";      //작성중
        		break;
        	case "R":
        		parentStatus = "RA";      //결재요청
        		break;
        	case "P":   
        		parentStatus = "PA";      //결재중
        		break;
        	case "C":
        		parentStatus = "C";       //결재완료
        		break;
        	case "D":
        		parentStatus = "DA";      //결재반려
        		break;
        	default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);    
        	CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
        	crs.appProcess(appReqDetailDTO, user, parentStatus, "ASSET");
        }
        
        //설비마스터 개정 완료
        if("REVEQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
            {
                case "W":
                    parentStatus = "W";      //작성중
                    break;
                case "R":
                    parentStatus = "R";      //결재요청
                    break;
                case "P":   
                    parentStatus = "PR";      //결재중
                    break;
                case "C":
                    parentStatus = "C";       //출고대기
                    break;
                case "D":
                    parentStatus = "D";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
            crs.appProcess(appReqDetailDTO, user, "W", "ASSET");
        }
        
        //설비마스터 제정 완료
        if("REVREQ".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> WOFMEA_STATUS
            {
                case "W":
                    parentStatus = "W";      //작성중
                    break;
                case "R":
                    parentStatus = "R";      //결재요청
                    break;
                case "P":   
                    parentStatus = "PR";      //결재중
                    break;
                case "C":
                    parentStatus = "C";       //출고대기
                    break;
                case "D":
                    parentStatus = "D";      //결재반려
                    break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
            crs.appProcess(appReqDetailDTO, user, "P", "ASSET");
        }

        // 점검실시 완료
        if("PMINSRSLT".equals(apprType))
        {
            switch(apprStatus)              //APPR_STATUS -> PMSCHED_STATUS
            {
                case "W":
                    parentStatus = "";      //작성
                    break;
                case "R":
                    parentStatus = "IRWRA";      // 결재요청
                    break;
                case "P":   
                    parentStatus = "IRWOA";      // 결재중
                    break;
                case "D":
                    parentStatus = "IRWDA";      // 결재반려
                    break;
                case "C":
                	parentStatus = "C";      	// 작업완료
                	break;
                default:
                    parentStatus = apprStatus;
                    break;
            }
            appReqDetailDTO.setParentStatus(parentStatus);
            WorkPmiDetailService wps = (WorkPmiDetailService)CommonUtil.getBean("workPmiDetailService", user);
            wps.appProcess(appReqDetailDTO, user);
        }
        
        // 점검주기설정 제개정 완료
        if("PMINSREV".equals(apprType))
        {
        	switch(apprStatus)              //APPR_STATUS -> REVISION_STATUS
        	{
        	case "W":
        		parentStatus = "W";      //작성중
        		break;
        	case "R":
        		parentStatus = "RA";      //결재요청
        		break;
        	case "P":   
        		parentStatus = "PA";      //결재중
        		break;
        	case "C":
        		parentStatus = "C";       //결재완료
        		break;
        	case "D":
        		parentStatus = "DA";      //결재반려
        		break;
    		default:
        		parentStatus = apprStatus;
        		break;
        	}
        	appReqDetailDTO.setParentStatus(parentStatus);
        	CommRevService crs = (CommRevService)CommonUtil.getBean("commRevService", user);
        	crs.appProcess(appReqDetailDTO, user, parentStatus, "PMSTD");
        }
        
        MailUtil.sendMail("APP", appReqDetailDTO.getApprlistId(), user);
    }

	@Override
	public String findTotalCount(AppReadyCommonDTO appReadyCommonDTO, User user)
	{
		return appReadyListDAO.findTotalCount(appReadyCommonDTO, user);
	}
}