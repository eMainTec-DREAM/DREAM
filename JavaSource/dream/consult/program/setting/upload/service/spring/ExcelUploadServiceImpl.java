package dream.consult.program.setting.upload.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.consult.program.menu.dto.MaMenuMngCommonDTO;
import dream.consult.program.menu.service.MaMenuMngListService;
import dream.consult.program.setting.upload.service.ExcelUploadService;
import dream.mgr.cdsys.dto.MgrCdSysCodeListDTO;
import dream.mgr.cdsys.dto.MgrCdSysCommonDTO;
import dream.mgr.cdsys.service.MgrCdSysCodeListService;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MaUserDetailDTO;
import dream.mgr.user.service.MaUserDetailService;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.service.MaUsrGrpDetailService;
import dream.org.emp.dto.MaEmpCommonDTO;
import dream.org.emp.service.MaEmpDetailService;

/**
 * Excel Upload Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="excelUploadServiceTarget"
 * @spring.txbn id="excelUploadService"
 */
public class ExcelUploadServiceImpl implements ExcelUploadService
{
    String message = "";
    String isSuccess = "Y";

    @Override
    public Map upload(String excelTabNo, Map<String, Object> dataMap, User user) throws Exception
    {
        message = "";
        isSuccess = "Y";
        
        if("USER".equals(excelTabNo)) {
            uploadUSER(dataMap, user);
        }
        else {
            isSuccess = "N";
            message = "No Upload Program.";
        }
        Map resultMap = new HashMap();
        resultMap.put("SKEY_ID", StringUtil.valueOf(dataMap.get("SKEY_ID")));
        resultMap.put("MESSAGE", message);
        resultMap.put("IS_SUCCESS", isSuccess);
        
        return resultMap;
    }
    
    private void uploadUSER(Map<String, Object> dataMap, User user) throws Exception
    {
        String userId = StringUtil.valueOf(dataMap.get("USER_ID"));
        String userName = StringUtil.valueOf(dataMap.get("USER_NAME"));
        String usrGrpNo = StringUtil.valueOf(dataMap.get("USRGRP_NO"));
        String usrGrpName = StringUtil.valueOf(dataMap.get("USRGRP_NAME"));
        String usrGrpId = "";
        String initMenuNo = StringUtil.valueOf(dataMap.get("INIT_MENU_NO"));
        String initMenuDesc = StringUtil.valueOf(dataMap.get("INIT_MENU_DESC"));
        String initMenuId = "";
        String empNo = StringUtil.valueOf(dataMap.get("EMP_NO"));
        String empName = StringUtil.valueOf(dataMap.get("EMP_NAME"));
        String empId = "";
        String securGrade = StringUtil.valueOf(dataMap.get("SECUR_GRADE"));
        String menuDisplay = StringUtil.valueOf(dataMap.get("MENU_DISPLAY"));
        String isMonitor = StringUtil.valueOf(dataMap.get("IS_MONITOR")).toUpperCase();
        String alarmViewYn = StringUtil.valueOf(dataMap.get("ALARM_VIEW_YN")).toUpperCase();
        
        StringBuilder msgBuilder = new StringBuilder();
        
        if("".equals(userId)) {
            isSuccess = "N";
            msgBuilder.append(",사용자id 확인");
        }
        
        if("".equals(userName)) {
            isSuccess = "N";
            msgBuilder.append(",사용자명 확인");
        }
        
        if("".equals(usrGrpNo)) {
            isSuccess = "N";
            msgBuilder.append(",권한코드 확인");
        }
        else {
            MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService) CommonUtil.getBean("maUsrGrpDetailService", user);
            MaUsrGrpCommonDTO maUsrGrpCommonDTO = new MaUsrGrpCommonDTO();
            maUsrGrpCommonDTO.setUsrGrpNo(usrGrpNo);
            usrGrpId = maUsrGrpDetailService.findDetail(maUsrGrpCommonDTO, user).getUsrGrpId();
            if("".equals(usrGrpId)) {
                isSuccess = "N";
                msgBuilder.append(",권한코드 확인");
            }
        }
        
        if(!"".equals(initMenuNo)) {
            MaUsrGrpDetailService maUsrGrpDetailService = (MaUsrGrpDetailService) CommonUtil.getBean("maUsrGrpDetailService", user);
            MaUsrGrpCommonDTO maUsrGrpCommonDTO = new MaUsrGrpCommonDTO();
            maUsrGrpCommonDTO.setCompNo(user.getCompNo());
            maUsrGrpCommonDTO.setUsrGrpId(usrGrpId);
            maUsrGrpCommonDTO.setMenuNo(initMenuNo);
            List<Map> menuList = maUsrGrpDetailService.findMenuList(maUsrGrpCommonDTO);
            if(menuList.size() > 0){
                initMenuId = StringUtil.valueOf(menuList.get(0).get("MENUID"));
            }
            else{
                isSuccess = "N";
                msgBuilder.append(",초기화면 확인");
            }
        }
        else {
            MaMenuMngListService maMenuMngListService = (MaMenuMngListService) CommonUtil.getBean("maMenuMngListService", user);
            MaMenuMngCommonDTO maMenuMngCommonDTO = new MaMenuMngCommonDTO();
            maMenuMngCommonDTO.setFilterMenuNo("^AN140");
            List<Map> menuList = maMenuMngListService.findMenuList(maMenuMngCommonDTO, user);
            if(menuList.size() > 0){
                initMenuId = StringUtil.valueOf(menuList.get(0).get("MENUID"));
            }
        }
        
        if("".equals(empNo)) {
            isSuccess = "N";
            msgBuilder.append(",사원번호 확인");
        }
        else {
            MaEmpDetailService maEmpDetailService = (MaEmpDetailService) CommonUtil.getBean("maEmpDetailService", user);
            MaEmpCommonDTO maEmpCommonDTO = new MaEmpCommonDTO();
            maEmpCommonDTO.setFilterEmpNo("^"+empNo);
            empId = maEmpDetailService.findDetail(maEmpCommonDTO, user).getEmpId();
            if("".equals(empId)) {
                isSuccess = "N";
                msgBuilder.append(",사원번호 확인");
            }
        }
        
        message = msgBuilder.toString();
        message = message.startsWith(",")?message.substring(1):message;
        
        if("Y".equals(isSuccess)) {
            if(!"Y".equals(alarmViewYn) && !"N".equals(alarmViewYn)) alarmViewYn = "Y";
            if(!"Y".equals(isMonitor) && !"N".equals(isMonitor)) isMonitor = "Y";
            
            MgrCdSysCodeListService mgrCdSysCodeListService = (MgrCdSysCodeListService) CommonUtil.getBean("mgrCdSysCodeListService", user);
            MgrCdSysCodeListDTO mgrCdSysCodeListDTO = new MgrCdSysCodeListDTO();
            mgrCdSysCodeListDTO.setListType("MENU_DISPLAY");
            mgrCdSysCodeListDTO.setCdSysDNo(menuDisplay);
            if(mgrCdSysCodeListService.findCodeList(new MgrCdSysCommonDTO(), mgrCdSysCodeListDTO).size()==0) menuDisplay = "EXPAND";
            
            mgrCdSysCodeListDTO.setListType("SECUR_GRADE");
            mgrCdSysCodeListDTO.setCdSysDNo(securGrade);
            if(mgrCdSysCodeListService.findCodeList(new MgrCdSysCommonDTO(), mgrCdSysCodeListDTO).size()==0) securGrade = "3";
            
            MaUserDetailService maUserDetailService = (MaUserDetailService) CommonUtil.getBean("maUserDetailService", user);
            MaUserCommonDTO maUserCommonDTO = new MaUserCommonDTO();
            maUserCommonDTO.setFilterCompNo(user.getCompNo());
            maUserCommonDTO.setCompNo(user.getCompNo());
            maUserCommonDTO.setFilterUserNo("^"+userId);
            MaUserDetailDTO maUserDetailDTO = maUserDetailService.findDetail(maUserCommonDTO, user);
            if("".equals(maUserDetailDTO.getUserId())) {
                maUserDetailDTO.setCompNo(user.getCompNo());
                maUserDetailDTO.setUserId(maUserDetailService.getNextSequence());
                maUserDetailDTO.setUserNo(userId);
                maUserDetailDTO.setUserName(userName);
                maUserDetailDTO.setUsrGrpId(usrGrpId);
                maUserDetailDTO.setInitMenuId(initMenuId);
                maUserDetailDTO.setEmpId(empId);
                maUserDetailDTO.setSecurGradeId(securGrade);
                maUserDetailDTO.setMenuDisplayId(menuDisplay);
                maUserDetailDTO.setIsMonitor(isMonitor);
                maUserDetailDTO.setAlarmViewYn(alarmViewYn);
                maUserDetailDTO.setIsUse("Y");
                
                maUserDetailService.insertDetail(maUserDetailDTO, user);
            }
            else {
                maUserDetailDTO.setUserName(userName);
                maUserDetailDTO.setUsrGrpId(usrGrpId);
                maUserDetailDTO.setInitMenuId(initMenuId);
                maUserDetailDTO.setEmpId(empId);
                maUserDetailDTO.setSecurGradeId(securGrade);
                maUserDetailDTO.setMenuDisplayId(menuDisplay);
                maUserDetailDTO.setIsMonitor(isMonitor);
                maUserDetailDTO.setAlarmViewYn(alarmViewYn);
                
                maUserDetailService.updateDetail(maUserDetailDTO, user);
            }
        }
    }
}
