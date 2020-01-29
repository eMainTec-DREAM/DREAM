package dream.req.work.service.spring;

import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.invt.list.service.InvtDetailService;
import dream.req.work.dao.MaWoReqResListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.MaWoReqResListDTO;
import dream.req.work.service.MaWoReqDetailService;
import dream.req.work.service.MaWoReqResDetailService;
import dream.req.work.service.MaWoReqResListService;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanDetailDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import dream.work.list.service.WoPlanDetailService;

/**
 * 목록 serviceimpl
 * @author kim21017
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maWoReqResListServiceTarget"
 * @spring.txbn id="maWoReqResListService"
 * @spring.property name="maWoReqResListDAO" ref="maWoReqResListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="woPlanDetailService" ref="woPlanDetailService"
 * @spring.property name="invtDetailService" ref="invtDetailService"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 */
public class MaWoReqResListServiceImpl implements MaWoReqResListService
{
    private MaWoReqResListDAO maWoReqResListDAO                     = null;
    
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
    private MaWoReqResDetailService maWoReqResDetailService         = null;
    
    private InvtDetailService invtDetailService                     = null;
    
    private WoPlanDetailService woPlanDetailService                 = null;
    
    public WoPlanDetailService getWoPlanDetailService() {
        return woPlanDetailService;
    }

    public void setWoPlanDetailService(WoPlanDetailService woPlanDetailService) {
        this.woPlanDetailService = woPlanDetailService;
    }

    public InvtDetailService getInvtDetailService() {
        return invtDetailService;
    }

    public void setInvtDetailService(InvtDetailService invtDetailService) {
        this.invtDetailService = invtDetailService;
    }

    public MaWoReqResDetailService getMaWoReqResDetailService() {
        return maWoReqResDetailService;
    }

    public void setMaWoReqResDetailService(MaWoReqResDetailService maWoReqResDetailService) {
        this.maWoReqResDetailService = maWoReqResDetailService;
    }

    public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }

    public MaWoReqResListDAO getMaWoReqResListDAO() {
        return maWoReqResListDAO;
    }

    public void setMaWoReqResListDAO(MaWoReqResListDAO maWoReqResListDAO) {
        this.maWoReqResListDAO = maWoReqResListDAO;
    }

    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,MaWoReqResListDTO maWoReqResListDTO, User user)
    {      
        return maWoReqResListDAO.findList(maWoReqCommonDTO,maWoReqResListDTO,user);
    }

    public int deleteList(String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maWoReqResListDAO.deleteList(id,user.getCompNo());
            }
        return result;
    }

    @Override
    public String linkWo(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception {
        maWoReqResDetailService = (MaWoReqResDetailService) CommonUtil.getBean("maWoReqResDetailService", user);

        MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
        maWoResultMstrCommonDTO.setWkOrId(maWoReqCommonDTO.getWkorId());
        maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
        maWoResultMstrCommonDTO.setUserLang(user.getLangId());
        // 조회된 상세 부분
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
      
        MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        maWoReqResDetailDTO.setWoReqResId(maWoReqResListDAO.getNextSequence("SQAWOREQRES_ID"));
        //maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd")); // 작업일자로 변경해야 함.
        maWoReqResDetailDTO.setResDate(maWoResultMstrDetailDTO.getWkorDate()); // 작업일자로 변경해야 함.
        maWoReqResDetailDTO.setWoreqresMethod("WO");
        maWoReqResDetailDTO.setWoReqGenType("EM"); //사후연결처리..
        
        if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
    	{
    		maWoReqResDetailDTO.setResStatusId("COM"); //작업중
    	}
    	else
    	{
    		maWoReqResDetailDTO.setResStatusId("WRK"); //작업중
    	}
        
        maWoReqResDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
        maWoReqResDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
        maWoReqResDetailDTO.setResponse(maWoResultMstrDetailDTO.getWkOrDesc());
        maWoReqResDetailDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());
        maWoReqCommonDTO.setCompNo(maWoResultMstrCommonDTO.getCompNo());
        
        maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
        
        // 요청 상태 체크 후 상태 변경
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", user);
        maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
        maWoReqDetailService.checkStatus(maWoReqCommonDTO, user);
        
        return maWoReqResDetailDTO.getResStatusId();
    }
    
    @Override
    public void linkInvt(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception {

        InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
        invtCommonDTO.setInvtlistId(maWoReqCommonDTO.getInvtlistId());
        invtCommonDTO.setCompNo(user.getCompNo());
        invtCommonDTO.setUserLang(user.getLangId());
        
        // 조회된 상세 부분
        InvtDetailDTO invtDetailDTO = invtDetailService.findDetail(invtCommonDTO, user);
        
        MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        maWoReqResDetailDTO.setWoReqResId(maWoReqResListDAO.getNextSequence("SQAWOREQRES_ID"));
        maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
        
        if("W".equals(invtDetailDTO.getInvtlistStatus()))
        {
            maWoReqResDetailDTO.setResStatusId("REV"); //검토중
        }
        else if("C".equals(invtDetailDTO.getInvtlistStatus()))
        {
            maWoReqResDetailDTO.setResStatusId("COM"); //작업완료
        }
        else 
        {
            maWoReqResDetailDTO.setResStatusId("WRK"); //작업중
        }
        
        maWoReqResDetailDTO.setDeptId(invtDetailDTO.getDeptId());
        maWoReqResDetailDTO.setEmpId(invtDetailDTO.getEmpId());
        maWoReqResDetailDTO.setResponse(invtDetailDTO.getDescription());
        maWoReqResDetailDTO.setInvtlistId(invtDetailDTO.getInvtlistId());
        maWoReqResDetailDTO.setWoreqresMethod("INVT");
        maWoReqResDetailDTO.setWoReqGenType("EM"); //사후연결처리..

        maWoReqCommonDTO.setCompNo(user.getCompNo());
        
        maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
        
        // 요청 상태 체크 후 상태 변경
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", user);
        maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
        maWoReqDetailService.checkStatus(maWoReqCommonDTO, user);
    }
    
    @Override
    public void linkWoPlan(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception {
       
       WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
       woPlanCommonDTO.setWkOrId(maWoReqCommonDTO.getWkorId());
       woPlanCommonDTO.setCompNo(user.getCompNo());
       woPlanCommonDTO.setUserLang(user.getLangId());

       // 조회된 상세 부분
       WoPlanDetailDTO woPlanDetailDTO = woPlanDetailService.findDetail(woPlanCommonDTO, user);
       
       MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
       maWoReqResDetailDTO.setWoReqResId(maWoReqResListDAO.getNextSequence("SQAWOREQRES_ID"));
       //maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd")); // 작업일자로 변경해야 함.
       maWoReqResDetailDTO.setResDate(woPlanDetailDTO.getWkorDate()); // 작업일자로 변경해야 함.
       maWoReqResDetailDTO.setWoreqresMethod("WOPLAN");
       maWoReqResDetailDTO.setWoReqGenType("EM"); //사후연결처리..
       
       if("PPC".equals(woPlanDetailDTO.getWoPlanStatusId()))
       {
          maWoReqResDetailDTO.setResStatusId("COM"); //작업중
       }
       else
       {
          maWoReqResDetailDTO.setResStatusId("WRK"); //작업중
       }
       
       maWoReqResDetailDTO.setDeptId(woPlanDetailDTO.getDeptId());
       maWoReqResDetailDTO.setEmpId(woPlanDetailDTO.getEmpId());
       maWoReqResDetailDTO.setResponse(woPlanDetailDTO.getWkOrDesc());
       maWoReqResDetailDTO.setWkorId(woPlanDetailDTO.getWkOrId());
       
       maWoReqCommonDTO.setCompNo(woPlanCommonDTO.getCompNo());
       
       maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
    }

    
    @Override
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, MaWoReqResListDTO maWoReqResListDTO, User user)
            throws Exception {
        return maWoReqResListDAO.findTotalCount(maWoReqCommonDTO, maWoReqResListDTO, user);
    }
}

