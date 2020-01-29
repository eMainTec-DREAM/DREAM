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
 * ��� serviceimpl
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
        // ��ȸ�� �� �κ�
        MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
      
        ReqWoInvtRsltDetailDTO reqWoInvtRsltDetailDTO = new ReqWoInvtRsltDetailDTO();
        reqWoInvtRsltDetailDTO.setWoReqResId(reqWoInvtRsltListDAO.getNextSequence("SQAWOREQRES_ID"));
        //reqWoInvtRsltDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd")); // �۾����ڷ� �����ؾ� ��.
        reqWoInvtRsltDetailDTO.setResDate(maWoResultMstrDetailDTO.getWkorDate()); // �۾����ڷ� �����ؾ� ��.
        reqWoInvtRsltDetailDTO.setWoReqGenType("EM"); //���Ŀ���ó��..

        if("C".equals(maWoResultMstrDetailDTO.getWoStatusId())||"PRC".equals(maWoResultMstrDetailDTO.getWoStatusId()))
        {
            reqWoInvtRsltDetailDTO.setResStatusId("COM"); //�۾���
        }
        else
        {
            reqWoInvtRsltDetailDTO.setResStatusId("WRK"); //�۾���
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

        // ��ȸ�� �� �κ�
        InvtDetailDTO invtDetailDTO = invtDetailService.findDetail(invtCommonDTO, user);
        
        MaWoReqResDetailDTO maWoReqResDetailDTO = new MaWoReqResDetailDTO();
        
        String woreqresId = reqWoInvtRsltListDAO.getNextSequence("SQAWOREQRES_ID");
//      maWoReqResDetailDTO.setWoReqResId(reqWoInvtRsltListDAO.getNextSequence("SQAWOREQRES_ID"));
        maWoReqResDetailDTO.setWoReqResId(woreqresId);
        maWoReqResDetailDTO.setResDate(DateUtil.getDateTime("yyyy-MM-dd"));
        maWoReqResDetailDTO.setWoreqresMethod("INVT");
        maWoReqResDetailDTO.setWoReqGenType("EM"); //���Ŀ���ó��..
//      if("W".equals(invtDetailDTO.getInvtlistStatus()))
//      {
//          maWoReqResDetailDTO.setResStatusId("REV"); //������
//      }
//      else 
        if("C".equals(invtDetailDTO.getInvtlistStatus()))
        {
            maWoReqResDetailDTO.setResStatusId("COM"); //�۾��Ϸ�
        }
        else 
        {
            maWoReqResDetailDTO.setResStatusId("WRK"); //�۾���
        }
        
        maWoReqResDetailDTO.setDeptId(invtDetailDTO.getDeptId());
        maWoReqResDetailDTO.setEmpId(invtDetailDTO.getEmpId());
        maWoReqResDetailDTO.setResponse(invtDetailDTO.getDescription());
        maWoReqResDetailDTO.setInvtlistId(maWoReqCommonDTO.getInvtlistId());

        maWoReqCommonDTO.setCompNo(user.getCompNo());
        maWoReqCommonDTO.setWoReqResId(maWoReqResDetailDTO.getWoReqResId());
            
        //invtlistId�� ��û������ ������Ʈ �Ѵ�. for daewoong?!!?!
        
        maWoReqResDetailService.insertDetail(maWoReqResDetailDTO, maWoReqCommonDTO);
    }

    @Override
    public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user)
            throws Exception {
        return reqWoInvtRsltListDAO.findTotalCount(maWoReqCommonDTO, reqWoInvtRsltListDTO, user);
    }

}