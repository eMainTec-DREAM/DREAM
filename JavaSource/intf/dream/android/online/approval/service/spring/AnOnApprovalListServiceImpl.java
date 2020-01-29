package intf.dream.android.online.approval.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;
import dream.pers.appstb.ready.service.AppReadyListService;
import intf.dream.android.online.approval.dao.AnOnApprovalListDAO;
import intf.dream.android.online.approval.service.AnOnApprovalListService;
/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnApprovalListServiceTarget"
 * @spring.txbn id="anOnApprovalListService"
 * @spring.property name="anOnApprovalListDAO" ref="anOnApprovalListDAO"
 * @spring.property name="appReadyListService" ref="appReadyListService"
 */
public class AnOnApprovalListServiceImpl implements AnOnApprovalListService {
    private AnOnApprovalListDAO anOnApprovalListDAO = null;
    private AppReadyListService appReadyListService = null;

    public AppReadyListService getAppReadyListService() {
        return this.appReadyListService;
    }

    public void setAppReadyListService(AppReadyListService appReadyListService) {
        this.appReadyListService = appReadyListService;
    }

    public AnOnApprovalListDAO getAnOnApprovalListDAO() {
        return this.anOnApprovalListDAO;
    }

    public void setAnOnApprovalListDAO(AnOnApprovalListDAO anOnApprovalListDAO) {
        this.anOnApprovalListDAO = anOnApprovalListDAO;
    }

    public List findApprovalList(Map map) throws Exception {
        return this.anOnApprovalListDAO.findApprovalList(map);
    }

    public List findApprovalUserList(Map map) throws Exception {
        return this.anOnApprovalListDAO.findApprovalUserList(map);
    }

    public List findApprovalLineList(Map map) throws Exception {
        return this.anOnApprovalListDAO.findApprovalLineList(map);
    }

    public List findApprovalLineUserList(Map map) throws Exception {
        return this.anOnApprovalListDAO.findApprovalLineUserList(map);
    }

    public List findApprovalUserNextNo(Map map) throws Exception {
        return this.anOnApprovalListDAO.findApprovalUserNextNo(map);
    }

    public int insertApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.insertApproval(map);
        }
        return resultQty;
    }

    public int updateApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.updateApproval(map);
        }
        return resultQty;
    }

    public int insertApprovalUser(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.insertApprovalUser(map);
        }
        return resultQty;
    }

    public int updateApprovalUser(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.updateApprovalUser(map);
        }
        return resultQty;
    }

    public int deleteApprovalUser(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.deleteApprovalUser(map);
        }
        return resultQty;
    }

    public int addApprovalLine(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.addApprovalLine(map);
        }
        return resultQty;
    }

    public int reqApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.anOnApprovalListDAO.addReqUser(map);
            this.anOnApprovalListDAO.updateReqStatus(map);
            this.anOnApprovalListDAO.updateNextUsrStatus(map);
            this.anOnApprovalListDAO.initApprUser(map);
            User user = new User();
            user.setCompNo(CommonUtil.convertString(map.get("compNo")));
            user.setEmpId(CommonUtil.convertString(map.get("empId")));
            user.setUserId(CommonUtil.convertString(map.get("userId")));
            AppReqDetailDTO apprReqDetailDTO = new AppReqDetailDTO();
            apprReqDetailDTO.setApprStatus("R");
            apprReqDetailDTO.setApprType(CommonUtil.convertString(map.get("apprType")));
            apprReqDetailDTO.setApprlistId(CommonUtil.convertString(map.get("apprListId")));
            apprReqDetailDTO.setObjectId(CommonUtil.convertString(map.get("objectId")));
            this.appReadyListService.apprProcess(apprReqDetailDTO, user);
        }
        return resultQty;
    }

    public List findApprovalReadyList(Map map) throws Exception {
        return this.anOnApprovalListDAO.findApprovalReadyList(map);
    }

    public int actionApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            String[] apprusrIds = new String[]{CommonUtil.convertString(map.get("apprUsrId"))};
            String[] apprlistIds = new String[]{CommonUtil.convertString(map.get("apprListId"))};
            User user = new User();
            user.setCompNo(CommonUtil.convertString(map.get("compNo")));
            user.setEmpId(CommonUtil.convertString(map.get("empId")));
            user.setUserId(CommonUtil.convertString(map.get("userId")));
            AppReadyCommonDTO appReadyCommonDTO = new AppReadyCommonDTO();
            appReadyCommonDTO.setRemark(CommonUtil.convertString(map.get("remark")));
            resultQty += this.appReadyListService.approveLine(apprusrIds, apprlistIds, user, appReadyCommonDTO);
        }
        return resultQty;
    }

    public int referenceApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            String[] apprusrIds = new String[]{CommonUtil.convertString(map.get("apprUsrId"))};
            String[] apprlistIds = new String[]{CommonUtil.convertString(map.get("apprListId"))};
            User user = new User();
            user.setCompNo(CommonUtil.convertString(map.get("compNo")));
            user.setEmpId(CommonUtil.convertString(map.get("empId")));
            user.setUserId(CommonUtil.convertString(map.get("userId")));
            AppReadyCommonDTO appReadyCommonDTO = new AppReadyCommonDTO();
            appReadyCommonDTO.setRemark(CommonUtil.convertString(map.get("remark")));
            resultQty += this.appReadyListService.referenceLine(apprusrIds, apprlistIds, user, appReadyCommonDTO);
        }
        return resultQty;
    }

    public int rejectApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            String[] apprusrIds = new String[]{CommonUtil.convertString(map.get("apprUsrId"))};
            String[] apprlistIds = new String[]{CommonUtil.convertString(map.get("apprListId"))};
            User user = new User();
            user.setCompNo(CommonUtil.convertString(map.get("compNo")));
            user.setEmpId(CommonUtil.convertString(map.get("empId")));
            user.setUserId(CommonUtil.convertString(map.get("userId")));
            AppReadyCommonDTO appReadyCommonDTO = new AppReadyCommonDTO();
            appReadyCommonDTO.setRemark(CommonUtil.convertString(map.get("remark")));
            resultQty += this.appReadyListService.rejectLine(apprusrIds, apprlistIds, user, appReadyCommonDTO);
        }
        return resultQty;
    }
}
