package dream.app;

//import dream.bm.day.service.BmDayDetailService;
//import dream.hse.rev.service.HseRevDetailService;
//import dream.pt.pur.service.PtPurDetailService;
//import dream.wo.pjt.service.WoPjtDetailService;
//import dream.wo.plan.service.WoPlanDetailService;
//import dream.wo.ptw.service.WoPtwDetailService;
//import dream.wo.req.service.WoReqDetailService;
//import dream.wo.result.service.WoResultDetailService;
//import dream.wo.serv.service.WoServDetailService;

/**
 * 결재문서 처리
 * @author  javaworker
 * @version $Id: AppObjServiceImpl.java,v 1.6 2014/02/11 00:22:44 pochul2423 Exp $
 * @since   1.0
 * @spring.bean id="appObjServiceTarget"
 * @spring.txbn id="appObjService"
// * @spring.property name="woReqDetailService" ref="woReqDetailService"
// * @spring.property name="woPlanDetailService" ref="woPlanDetailService"
// * @spring.property name="woResultDetailService" ref="woResultDetailService"
// * @spring.property name="bmDayDetailService" ref="bmDayDetailService"
// * @spring.property name="ptPurDetailService" ref="ptPurDetailService"
// * @spring.property name="woPjtDetailService" ref="woPjtDetailService"
// * @spring.property name="woPtwDetailService" ref="woPtwDetailService"
// * @spring.property name="woServDetailService" ref="woServDetailService"
// * @spring.property name="hseRevDetailService" ref="hseRevDetailService"
 */
public class AppObjServiceImpl implements AppObjService
{

    @Override
    public void callService(AppObjDTO appObjDTO)
    {
        // TODO Auto-generated method stub
        
    }
    /** 작업요청 */
//    private WoReqDetailService woReqDetailService = null;
//    /** 작업설계 */
//    private WoPlanDetailService woPlanDetailService = null;
//    /** 작업실적 */
//    private WoResultDetailService woResultDetailService = null;
//    /** 일일정비회의 */
//    private BmDayDetailService bmDayDetailService = null;
//    /** Purchase Request */
//    private PtPurDetailService ptPurDetailService = null;
//    /** 공사관리 */
//    private WoPjtDetailService woPjtDetailService = null;
//    /** 작업허가서 */
//    private WoPtwDetailService woPtwDetailService = null;
//    /** Service 요청 */
//    private WoServDetailService woServDetailService = null;
//    /** Request for Revision */
//    private HseRevDetailService hseRevDetailService = null;
//    
//    public void callService(AppObjDTO appObjDTO)
//    {   
//        /*
//         * 결재처리상태[app_status]
//         * A   기안
//         * B   결재처리
//         * D   반려
//         * E   결재취소
//         * C   승인완료
//         */
//        
//        // 작업요청
//        if("WOR".equals(appObjDTO.getWfType()))
//        {
//            woReqDetailService.appProc(appObjDTO);
//        }
//        // 작업설계
//        else if("WOP".equals(appObjDTO.getWfType()))
//        {
//            woPlanDetailService.appProc(appObjDTO);
//        }
//        // 작업실적
//        else if("WOC".equals(appObjDTO.getWfType()))
//        {
//            woResultDetailService.appProc(appObjDTO);
//        }
//        // 일일정비회의
//        else if("BMD".equals(appObjDTO.getWfType()))
//        {
//        	bmDayDetailService.appProc(appObjDTO);
//        }
//        //자재구매요청관리
//        else if("PUR".equals(appObjDTO.getWfType()))
//        {
//            ptPurDetailService.appProc(appObjDTO);
//        }
//        // 프로젝트 관리(PJTL:공사설계, PJTC:공사완료)
//        else if("PJTL".equals(appObjDTO.getWfType()) || "PJTC".equals(appObjDTO.getWfType()))
//        {
//            woPjtDetailService.appProc(appObjDTO);
//        }
//        // Service 요청
//        else if("SERV".equals(appObjDTO.getWfType()))
//        {
//            woServDetailService.appProc(appObjDTO);
//        }
//        // Service 요청
//        else if("HSEREV".equals(appObjDTO.getWfType()))
//        {
//            hseRevDetailService.appProc(appObjDTO);
//        }
//    }
//   
//    public WoReqDetailService getWoReqDetailService()
//    {
//        return woReqDetailService;
//    }
//
//    public void setWoReqDetailService(WoReqDetailService woReqDetailService)
//    {
//        this.woReqDetailService = woReqDetailService;
//    }
//
//    public WoPlanDetailService getWoPlanDetailService()
//    {
//        return woPlanDetailService;
//    }
//
//    public void setWoPlanDetailService(WoPlanDetailService woPlanDetailService)
//    {
//        this.woPlanDetailService = woPlanDetailService;
//    }
//
//    public WoResultDetailService getWoResultDetailService()
//    {
//        return woResultDetailService;
//    }
//
//    public void setWoResultDetailService(WoResultDetailService woResultDetailService)
//    {
//        this.woResultDetailService = woResultDetailService;
//    }
//
//    public BmDayDetailService getBmDayDetailService()
//    {
//        return bmDayDetailService;
//    }
//
//    public void setBmDayDetailService(BmDayDetailService bmDayDetailService)
//    {
//        this.bmDayDetailService = bmDayDetailService;
//    }
//
//    public PtPurDetailService getPtPurDetailService()
//    {
//        return ptPurDetailService;
//    }
//
//    public void setPtPurDetailService(PtPurDetailService ptPurDetailService)
//    {
//        this.ptPurDetailService = ptPurDetailService;
//    }
//
//    public WoPjtDetailService getWoPjtDetailService()
//    {
//        return woPjtDetailService;
//    }
//
//    public void setWoPjtDetailService(WoPjtDetailService woPjtDetailService)
//    {
//        this.woPjtDetailService = woPjtDetailService;
//    }
//
//    public WoPtwDetailService getWoPtwDetailService()
//    {
//        return woPtwDetailService;
//    }
//
//    public void setWoPtwDetailService(WoPtwDetailService woPtwDetailService)
//    {
//        this.woPtwDetailService = woPtwDetailService;
//    }
//
//    public WoServDetailService getWoServDetailService()
//    {
//        return woServDetailService;
//    }
//
//    public void setWoServDetailService(WoServDetailService woServDetailService)
//    {
//        this.woServDetailService = woServDetailService;
//    }
//
//    public HseRevDetailService getHseRevDetailService()
//    {
//        return hseRevDetailService;
//    }
//
//    public void setHseRevDetailService(HseRevDetailService hseRevDetailService)
//    {
//        this.hseRevDetailService = hseRevDetailService;
//    }   
}