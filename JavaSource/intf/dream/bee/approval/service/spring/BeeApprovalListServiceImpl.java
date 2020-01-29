package intf.dream.bee.approval.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.pers.appreq.dto.AppReqDetailDTO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;
import dream.pers.appstb.ready.service.AppReadyListService;
import intf.dream.bee.approval.dao.BeeApprovalListDAO;
import intf.dream.bee.approval.service.BeeApprovalListService;
/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeApprovalListServiceTarget"
 * @spring.txbn id="beeApprovalListService"
 * @spring.property name="beeApprovalListDAO" ref="beeApprovalListDAO"
 * @spring.property name="appReadyListService" ref="appReadyListService"
 */
public class BeeApprovalListServiceImpl implements BeeApprovalListService {
    private BeeApprovalListDAO beeApprovalListDAO = null;
    private AppReadyListService appReadyListService = null;

    public AppReadyListService getAppReadyListService() {
        return this.appReadyListService;
    }

    public void setAppReadyListService(AppReadyListService appReadyListService) {
        this.appReadyListService = appReadyListService;
    }

    public BeeApprovalListDAO getBeeApprovalListDAO() {
        return this.beeApprovalListDAO;
    }

    public void setBeeApprovalListDAO(BeeApprovalListDAO beeApprovalListDAO) {
        this.beeApprovalListDAO = beeApprovalListDAO;
    }

    public List findApprovalList(Map map) throws Exception {
        return this.beeApprovalListDAO.findApprovalList(map);
    }

    public List findApprovalUserList(Map map) throws Exception {
        return this.beeApprovalListDAO.findApprovalUserList(map);
    }

    public List findApprovalLineList(Map map) throws Exception {
        return this.beeApprovalListDAO.findApprovalLineList(map);
    }

    public List findApprovalLineUserList(Map map) throws Exception {
        return this.beeApprovalListDAO.findApprovalLineUserList(map);
    }

    public List findApprovalUserNextNo(Map map) throws Exception {
        return this.beeApprovalListDAO.findApprovalUserNextNo(map);
    }

    public int insertApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.insertApproval(map);
        }
        return resultQty;
    }

    public int updateApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.updateApproval(map);
        }
        return resultQty;
    }

    public int insertApprovalUser(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.insertApprovalUser(map);
        }
        return resultQty;
    }

    public int updateApprovalUser(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.updateApprovalUser(map);
        }
        return resultQty;
    }

    public int deleteApprovalUser(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.deleteApprovalUser(map);
        }
        return resultQty;
    }

    public int addApprovalLine(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.addApprovalLine(map);
        }
        return resultQty;
    }

    public int reqApproval(List list) throws Exception {
        int resultQty = 0;
        for (Object obj : list) {
            Map map = (Map)obj;
            resultQty += this.beeApprovalListDAO.addReqUser(map);
            this.beeApprovalListDAO.updateReqStatus(map);
            this.beeApprovalListDAO.updateNextUsrStatus(map);
            this.beeApprovalListDAO.initApprUser(map);
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
        return this.beeApprovalListDAO.findApprovalReadyList(map);
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
