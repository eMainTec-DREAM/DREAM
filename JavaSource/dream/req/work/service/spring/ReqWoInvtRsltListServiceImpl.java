package dream.req.work.service.spring;

import java.util.List;

import common.bean.User;
import common.util.DateUtil;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtDetailDTO;
import dream.invt.list.service.InvtDetailService;
import dream.req.work.dao.ReqWoInvtRsltListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.MaWoReqResDetailDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;
import dream.req.work.service.MaWoReqResDetailService;
import dream.req.work.service.ReqWoInvtRsltListService;
import dream.work.list.service.MaWoResultMstrDetailService;

/**
 * 목록 serviceimpl
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqWoInvtRsltListServiceTarget"
 * @spring.txbn id="reqWoInvtRsltListService"
 * @spring.property name="reqWoInvtRsltListDAO" ref="reqWoInvtRsltListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 * @spring.property name="maWoReqResDetailService" ref="maWoReqResDetailService"
 * @spring.property name="invtDetailService" ref="invtDetailService"
 */
public class ReqWoInvtRsltListServiceImpl implements ReqWoInvtRsltListService
{
    private ReqWoInvtRsltListDAO reqWoInvtRsltListDAO               = null;
    
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
    private MaWoReqResDetailService maWoReqResDetailService         = null;
    
    private InvtDetailService invtDetailService                     = null;
    
    public InvtDetailService getInvtDetailService() {
        return invtDetailService;
    }

    public MaWoReqResDetailService getMaWoReqResDetailService() {
        return maWoReqResDetailService;
    }

    public void setMaWoReqResDetailService(MaWoReqResDetailService maWoReqResDetailService) {
        this.maWoReqResDetailService = maWoReqResDetailService;
    }

    public void setInvtDetailService(InvtDetailService invtDetailService) {
        this.invtDetailService = invtDetailService;
    }


    public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
        return maWoResultMstrDetailService;
    }

    public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
        this.maWoResultMstrDetailService = maWoResultMstrDetailService;
    }

    public ReqWoInvtRsltListDAO getReqWoInvtRsltListDAO() {
        return reqWoInvtRsltListDAO;
    }

    public void setReqWoInvtRsltListDAO(ReqWoInvtRsltListDAO reqWoInvtRsltListDAO) {
        this.reqWoInvtRsltListDAO = reqWoInvtRsltListDAO;
    }

    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user)
    {      
        return reqWoInvtRsltListDAO.findList(maWoReqCommonDTO,reqWoInvtRsltListDTO,user);
    }

    public int deleteList(String[] deleteRows, User user)
    {
        int result = 0;
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + reqWoInvtRsltListDAO.deleteList(id, user.getCompNo());
            }
        return result;
    }

    @Override
    public void linkWo(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user) throws Exception {

        /*MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
        maWoResultMstrCommonDTO.setWkOrId(maWoReqCommonDTO.getWkorId());
        maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
        maWoResultMstrCommonDTO.setUserLang(user.getLangId());
        // 조회된 상세 부분
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
      
        ReqWoInvtRsltDetailDTO reqWoInvtRsltDetailDTO = new ReqWoInvtRsltDetailDTO();
        reqWoInvtRsltDetailDTO.setWoReqResId(reqWoInvtRsltListDAO.getNextSequence("SQAWOREQRES_ID"));
        //reqWoInvtRsltDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd")); // 작업일자로 변경해야 함.
        reqWoInvtRsltDetailDTO.setResDate(maWoResultMstrDetailDTO.getWkorDate()); // 작업일자로 변경해야 함.
        reqWoInvtRsltDetailDTO.setWoReqGenType("EM"); //사후연결처리..

        if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
        {
            reqWoInvtRsltDetailDTO.setResStatusId("COM"); //작업중
        }
        else
        {
            reqWoInvtRsltDetailDTO.setResStatusId("WRK"); //작업중
        }
        
        reqWoInvtRsltDetailDTO.setDeptId(maWoResultMstrDetailDTO.getDeptId());
        reqWoInvtRsltDetailDTO.setEmpId(maWoResultMstrDetailDTO.getEmpId());
        reqWoInvtRsltDetailDTO.setResponse(maWoResultMstrDetailDTO.getWkOrDesc());
        reqWoInvtRsltDetailDTO.setWkorId(maWoResultMstrDetailDTO.getWkOrId());

        maWoReqCommonDTO.setCompNo(maWoResultMstrCommonDTO.getCompNo());
        
        reqWoInvtRsltDetailService.insertDetail(reqWoInvtRsltDetailDTO, maWoReqCommonDTO);*/
    }
    
    @Override
    public void linkInvt(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user) throws Exception {

        InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
        invtCommonDTO.setInvtlistId(maWoReqCommonDTO.getInvtlistId());
        invtCommonDTO.setCompNo(user.getCompNo());
        invtCommonDTO.setUserLang(user.getLangId());

        // 조회된 상세 부분
        InvtDetailDTO invtDetailDTO = invtDetailService.findDetail(invtCommonDTO, user);
        
        MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        
        String woreqresId = reqWoInvtRsltListDAO.getNextSequence("SQAWOREQRES_ID");
//      maWoReqResDetailDTO.setWoReqResId(reqWoInvtRsltListDAO.getNextSequence("SQAWOREQRES_ID"));
        maWoReqResDetailDTO.setWoReqResId(woreqresId);
        maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
        maWoReqResDetailDTO.setWoreqresMethod("INVT");
        maWoReqResDetailDTO.setWoReqGenType("EM"); //사후연결처리..
//      if("W".equals(invtDetailDTO.getInvtlistStatus()))
//      {
//          maWoReqResDetailDTO.setResStatusId("REV"); //검토중
//      }
//      else 
        if("C".equals(invtDetailDTO.getInvtlistStatus()))
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
        maWoReqResDetailDTO.setInvtlistId(maWoReqCommonDTO.getInvtlistId());

        maWoReqCommonDTO.setCompNo(user.getCompNo());
        maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
            
        //invtlistId의 요청정보를 업데이트 한다. for daewoong?!!?!
        
        maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
    }

    @Override
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user)
            throws Exception {
        return reqWoInvtRsltListDAO.findTotalCount(maWoReqCommonDTO, reqWoInvtRsltListDTO, user);
    }

}