package dream.mgr.message.service.spring;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import common.bean.MwareConfig;
import common.bean.User;
import common.exception.SqlIgnoreException;
import common.report.HtmlReporter;
import common.util.CommonUtil;
import common.util.MessageUtil;
import dream.message.send.templates.KakaoAlarmTemplates;
import dream.mgr.message.dao.MgrMessageTransDetailDAO;
import dream.mgr.message.dto.MgrMessageTransCommonDTO;
import dream.mgr.message.dto.MgrMessageTransDetailDTO;
import dream.mgr.message.service.MgrMessageTransDetailService;

/**
 * Message Transfer Page - Detail Service implements
 * @author syyang
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="mgrMessageTransDetailServiceTarget"
 * @spring.txbn id="mgrMessageTransDetailService"
 * @spring.property name="mgrMessageTransDetailDAO" ref="mgrMessageTransDetailDAO"
 */
public class MgrMessageTransDetailServiceImpl implements MgrMessageTransDetailService
{
    private MgrMessageTransDetailDAO mgrMessageTransDetailDAO = null;
    
	public MgrMessageTransDetailDAO getMgrMessageTransDetailDAO() {
		return mgrMessageTransDetailDAO;
	}
	public void setMgrMessageTransDetailDAO(MgrMessageTransDetailDAO mgrMessageTransDetailDAO) {
		this.mgrMessageTransDetailDAO = mgrMessageTransDetailDAO;
	}
    
    public MgrMessageTransDetailDTO findDetail(MgrMessageTransCommonDTO mgrMessageTransCommonDTO, User user) throws Exception
    {
    	return mgrMessageTransDetailDAO.findDetail(mgrMessageTransCommonDTO, user);
    }
	@Override
	public int insertDetail(MgrMessageTransDetailDTO mgrMessageTransDetailDTO, User user) throws Exception {
		if ("Y".equals(MwareConfig.getIsUseMailService())){
			return mgrMessageTransDetailDAO.insertDetail(mgrMessageTransDetailDTO, user);
		}else{
			return 0;
		}
		
	}
	
	@Override
    public String sendMail(String messageObjectType, String objectId, String methodType, User user) throws SqlIgnoreException
    {
	    String rtn = "-1";
	    try{
	        Method method = this.getClass().getMethod(messageObjectType, String.class, String.class, User.class);
	        rtn = (String) method.invoke(this, objectId, "MAIL", user);
	    }catch(Exception e){
	        throw new SqlIgnoreException(e.getMessage());
	    }
	    return rtn;
    }
	
	@Override
	public void makeKakaoMessage(String messageObjectType, String objectId, String methodType, User user) throws SqlIgnoreException{
		try{
			Method method = this.getClass().getMethod(messageObjectType, String.class, String.class, User.class);
	        method.invoke(this, objectId, methodType, user);
		}catch(Exception e){
	        throw new SqlIgnoreException(e.getMessage());
	    }
	}
	
	@Override
    public File getFile(String messageObjectType, User user)
	{
	    String enhancePath ="";
        String originPath ="";
        
        List<Map> compList = MwareConfig.getCompanies();
        String packageNo = "";
        for(Map compMap : compList)
        {
            if(user.getCompNo().equals(compMap.get("CODE")))
            {
                packageNo = compMap.get("PACKAGE_NO")==null?"":String.valueOf(compMap.get("PACKAGE_NO"));
            }
        }
        
        if(MwareConfig.osName.indexOf("LINUX") >=0){
            if(!"".equals(packageNo)) packageNo = "enhance/"+packageNo+"/";
            else packageNo = "dream/";
                
            enhancePath =  packageNo+"report/html/";
            originPath =  "dream/report/html/";
        }else{
            if(!"".equals(packageNo)) packageNo = "enhance\\"+packageNo+"\\";
            else packageNo = "dream\\";
            
            enhancePath =  packageNo+"report\\html\\";
            originPath =  "dream\\report\\html\\";
        }
        File input = new File(MwareConfig.getWebAppPath()+enhancePath + messageObjectType + ".html"); 
        if(!input.exists()) {
            input = new File(MwareConfig.getWebAppPath()+originPath + messageObjectType + ".html"); 
        }
        
        return input;
	}
    
	@Override
    public String insertDetail(String managerMailId, String contents, String title, User user ,String sendEmpNo, String recEmpNo, String msgObjType, String methodType, String objectId,  String objectNo)
    {
        MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
        
        String messagelistId = mgrMessageTransDetailDAO.getNextSequence("SQAMESSAGELIST_ID");
        mgrMessageTransDetailDTO.setMessageId(messagelistId);
        mgrMessageTransDetailDTO.setDescription(title);
        mgrMessageTransDetailDTO.setContents(contents);
        mgrMessageTransDetailDTO.setReceiver(managerMailId);
        mgrMessageTransDetailDTO.setMethodTypeId(methodType);
        mgrMessageTransDetailDTO.setMsgStatusId("Z");
        
        mgrMessageTransDetailDTO.setSendEmpNo(sendEmpNo);
        mgrMessageTransDetailDTO.setRecEmpNo(recEmpNo);
        mgrMessageTransDetailDTO.setMsgObjType(msgObjType);
        mgrMessageTransDetailDTO.setObjectId(objectId);
        mgrMessageTransDetailDTO.setObjectNo(objectNo);
        
        try
        {
        	insertDetail(mgrMessageTransDetailDTO, user);
        }
        catch (Exception e)
        {
            messagelistId = "0";
            e.printStackTrace();
        }
        return messagelistId;
    }

	@Override
    public void insertKakaoAlarmRequestLog(String messageListId, String managerMailId, String contents, String title, User user ,String sendEmpNo, String recEmpNo, String msgObjType, String methodType, String objectId,  String objectNo)
    {
        MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
        
        mgrMessageTransDetailDTO.setMessageId(messageListId);
        mgrMessageTransDetailDTO.setDescription(title);
        mgrMessageTransDetailDTO.setContents(contents);
        mgrMessageTransDetailDTO.setReceiver(managerMailId);
        mgrMessageTransDetailDTO.setMethodTypeId(methodType);
        mgrMessageTransDetailDTO.setMsgStatusId("Z");
        
        mgrMessageTransDetailDTO.setSendEmpNo(sendEmpNo);
        mgrMessageTransDetailDTO.setRecEmpNo(recEmpNo);
        mgrMessageTransDetailDTO.setMsgObjType(msgObjType);
        mgrMessageTransDetailDTO.setObjectId(objectId);
        mgrMessageTransDetailDTO.setObjectNo(objectNo);
        
        try
        {
        	mgrMessageTransDetailDAO.insertDetail(mgrMessageTransDetailDTO, user);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //결재 시 호출
    @Override
    public String APP(String apprListId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            Map apprList = (Map) mgrMessageTransDetailDAO.getApprList(apprListId, user).get(0);
            
            String apprStatus = apprList.get("APPR_STATUS")==null?"":apprList.get("APPR_STATUS").toString();
            
            if("C".equals(apprStatus) || "D".equals(apprStatus)) {
                APP20(apprList, methodType, user);
            }
            else {
                APP10(apprList, methodType, user);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //결재대기시 대기자에게 전송
    @Override
    public String APP10(Map apprList, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                
                String apprListId = apprList.get("APPRLIST_ID")==null?"":apprList.get("APPRLIST_ID").toString();
                String apprType = apprList.get("APPR_TYPE")==null?"":apprList.get("APPR_TYPE").toString();
                String apprStatus = apprList.get("APPR_STATUS")==null?"":apprList.get("APPR_STATUS").toString();
                String objectId = apprList.get("OBJECT_ID")==null?"":apprList.get("OBJECT_ID").toString();
                String title3 = apprList.get("TITLE")==null?"":apprList.get("TITLE").toString();
                
                String title1 = "";
                if(!"".equals(apprStatus)){
                    if("P".equals(apprStatus)) {
                        title1 = MessageUtil.getMessage(user.getLocale(), "CODESET", "APPR_STATUS.R");
                    }
                    else {
                        title1 = MessageUtil.getMessage(user.getLocale(), "CODESET", "APPR_STATUS."+apprStatus);
                    }
                }
                String title2 = "";
                Map data = new HashMap();
                HtmlReporter.Builder contentsBuilder = new HtmlReporter.Builder(getFile(messageObjectType+"_"+apprType, user));
                String objNo = "";
                List apprUsrList = mgrMessageTransDetailDAO.getApprUsrList(apprListId, user);
                switch(apprType){
                    case "REQWORK"://수리요청
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0271");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_REQWORK(objectId, user).get(0);
                        objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                        break;
                    case "REQINVTWORK"://투자요청
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0283");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_REQWORK(objectId, user).get(0);
                        objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                        break;
                    case "PTBUYREQ"://구매신청
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0276");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_PTBUYREQ(objectId, user).get(0);
                        List subList = mgrMessageTransDetailDAO.getSubListAPP10_PTBUYREQ(objectId, user);
                        contentsBuilder
                            .addList(subList);
                        objNo = data.get("PTPRLISTNO")==null?"":data.get("PTPRLISTNO").toString();
                        break;
                    case "PTSTKTAKE"://부품실사
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0284");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_PTSTKTAKE(objectId, user).get(0);
                        objNo = data.get("PTSTKTAKELIST_NO")==null?"":data.get("PTSTKTAKELIST_NO").toString();
                        break;
                    case "WORKORDER"://작업결과
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0275");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_WORKORDER(objectId, user).get(0);
                        objNo = data.get("WO_NO")==null?"":data.get("WO_NO").toString();
                        break;
                    default:
                        return "2";
                }
                
                String title = title1 + " - [" + title2 + "]" + title3;
                data.put("TITLE", title2);
                
                String contents = contentsBuilder
                        .data(data)
                        .addList(apprUsrList)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                
                List empList = mgrMessageTransDetailDAO.findNextApprUser(apprListId, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, user.getCompNo(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //결재완료시 기안자에게 전송
    @Override
    public String APP20(Map apprList, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                
                String apprListId = apprList.get("APPRLIST_ID")==null?"":apprList.get("APPRLIST_ID").toString();
                String apprType = apprList.get("APPR_TYPE")==null?"":apprList.get("APPR_TYPE").toString();
                String apprStatus = apprList.get("APPR_STATUS")==null?"":apprList.get("APPR_STATUS").toString();
                String objectId = apprList.get("OBJECT_ID")==null?"":apprList.get("OBJECT_ID").toString();
                String title3 = apprList.get("TITLE")==null?"":apprList.get("TITLE").toString();
                
                String title1 = "";
                if(!"".equals(apprStatus)){
                    if("P".equals(apprStatus)) {
                        title1 = MessageUtil.getMessage(user.getLocale(), "CODESET", "APPR_STATUS.R");
                    }
                    else {
                        title1 = MessageUtil.getMessage(user.getLocale(), "CODESET", "APPR_STATUS."+apprStatus);
                    }
                }
                String title2 = "";
                Map data = new HashMap();
                HtmlReporter.Builder contentsBuilder = new HtmlReporter.Builder(getFile("APP10_"+apprType, user));
                String objNo = "";
                List apprUsrList = mgrMessageTransDetailDAO.getApprUsrList(apprListId, user);
                switch(apprType){
                    case "REQWORK"://수리요청
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0271");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_REQWORK(objectId, user).get(0);
                        objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                        break;
                    case "REQINVTWORK"://투자요청
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0283");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_REQWORK(objectId, user).get(0);
                        objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                        break;
                    case "PTBUYREQ"://구매신청
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0276");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_PTBUYREQ(objectId, user).get(0);
                        List subList = mgrMessageTransDetailDAO.getSubListAPP10_PTBUYREQ(objectId, user);
                        contentsBuilder
                            .addList(subList);
                        objNo = data.get("PTPRLISTNO")==null?"":data.get("PTPRLISTNO").toString();
                        break;
                    case "PTSTKTAKE"://부품실사
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0284");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_PTSTKTAKE(objectId, user).get(0);
                        objNo = data.get("PTSTKTAKELIST_NO")==null?"":data.get("PTSTKTAKELIST_NO").toString();
                        break;
                    case "WORKORDER"://작업결과
                        title2 = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0275");
                        data = (Map) mgrMessageTransDetailDAO.getDataAPP10_WORKORDER(objectId, user).get(0);
                        objNo = data.get("WO_NO")==null?"":data.get("WO_NO").toString();
                        break;
                    default:
                        return "2";
                }
                
                String title = title1 + " - [" + title2 + "]" + title3;
                data.put("TITLE", title2);
                
                String contents = contentsBuilder
                        .data(data)
                        .addList(apprUsrList)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                
                List empList = mgrMessageTransDetailDAO.findApprDrafter(apprListId, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, user.getCompNo(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //작업요청시 접수자(부)에게 전송
    @Override
    public String REQ10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
        	String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
        	
        	if("MAIL".equals(methodType)){
        		if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                    String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0240");
                    Map data = (Map) mgrMessageTransDetailDAO.getDataREQ10(objectId, user).get(0);
                    String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                            .data(data)
                            .locale(user.getLocale())
                            .build();
                    String sendEmpNo = user.getEmpNo();
                    String objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                    
                    // 접수자가 지정되어 있다면 그 접수자한테만, 접수자가 없다면 작업그룹 전체 한테만, 작업그룹이 없다면 부서전체한테 메일 전송.
                    List empList = new ArrayList();
                    String recEmpId = data.get("REC_EMP_ID")==null?"":data.get("REC_EMP_ID").toString();
                    String recWkctrId = data.get("REC_WKCTR_ID")==null?"":data.get("REC_WKCTR_ID").toString();
                    String recDeptId = data.get("REC_DEPT_ID")==null?"":data.get("REC_DEPT_ID").toString();
                    
                    Map empParams = new HashMap();
                    empParams.put("COMP_NO", data.get("COMP_NO"));
                    if(!"".equals(recEmpId)) {
                        empParams.put("EMP_ID", recEmpId);
                        empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                    }
                    else if(!"".equals(recWkctrId)) {
                        empParams.put("WKCTR_ID", recWkctrId);
                        empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                    }
                    else if(!"".equals(recDeptId)) {
                        empParams.put("DEPT_ID", recDeptId);
                        empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                    }
                    
                    for(Object obj:empList.toArray()) {
                        Map empMap = (Map) obj;
                        if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                            String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                            String recEmpNo = empMap.get("EMP_NO").toString();
                            
                            insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                        }
                    }
                    rtn = "1";
                }
                else {
                    rtn = "2";
                }
        	}else if("KAKAO_ALARM".equals(methodType)){
        		if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
        			//요청데이터 생성
        			Map woReqData = (Map) mgrMessageTransDetailDAO.getDataREQ10(objectId, user).get(0);
        			
                    //전송자 데이터 생성
                    List empList = new ArrayList();
                    String recEmpId = woReqData.get("REC_EMP_ID")==null?"":woReqData.get("REC_EMP_ID").toString();
                    String recWkctrId = woReqData.get("REC_WKCTR_ID")==null?"":woReqData.get("REC_WKCTR_ID").toString();
                    String recDeptId = woReqData.get("REC_DEPT_ID")==null?"":woReqData.get("REC_DEPT_ID").toString();
                    Map empParams = new HashMap();
                    empParams.put("COMP_NO", woReqData.get("COMP_NO"));
                    if(!"".equals(recEmpId)) {
                        empParams.put("EMP_ID", recEmpId);
                        empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                    }
                    else if(!"".equals(recWkctrId)) {
                        empParams.put("WKCTR_ID", recWkctrId);
                        empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                    }
                    else if(!"".equals(recDeptId)) {
                        empParams.put("DEPT_ID", recDeptId);
                        empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                    }
                    
                    if(empList==null || empList.size()<1)return "EMPTY RECEIVER";
                    
                    //인증정보 획득
                    Map authData = (Map) mgrMessageTransDetailDAO.getMsgSendServCompData(user.getCompNo()).get(0);
        			//내용 만들기.
        			KakaoAlarmTemplates kakaoAlarmTemplates = new KakaoAlarmTemplates();
        			String contents = kakaoAlarmTemplates.REQ10_ko(woReqData, user);
                    
                    for(Object obj:empList.toArray()) {
                        Map empMap = (Map) obj;
                        if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
//                        	String phoneNumber = addCountryCode((String)empMap.get("COUNTRY_CODE"), (String)empMap.get("M_PHONE"));
                        	String countryCode = CommonUtil.convertString(empMap.get("COUNTRY_CODE"));
                        	String phoneNumber = addCountryCode(countryCode, CommonUtil.convertString(empMap.get("M_PHONE")));
                            String messageListId = mgrMessageTransDetailDAO.getNextSequence("sqamessagelist_id");
                            //로그 쌓기.
                            contents = contents.replaceAll("<br>", "\n").replace("&nbsp;", " ");
                            insertKakaoAlarmRequestLog(messageListId, phoneNumber, contents, "", user, user.getEmpNo() ,CommonUtil.convertString(empMap.get("EMP_NO")), messageObjectType, methodType, objectId, CommonUtil.convertString(woReqData.get("WOREQ_NO")));
                            
                            Map<String, String> sendData = new HashMap<>();
                            
                            sendData.put("ProgramType", authData.get("PROGRAM_TYPE")==null?"":authData.get("PROGRAM_TYPE").toString());
                            sendData.put("CompNo", woReqData.get("COMP_NO")==null?"":woReqData.get("COMP_NO").toString());
                            sendData.put("CompName", woReqData.get("COMP_NAME")==null?"":woReqData.get("COMP_NAME").toString());
                            sendData.put("SendEmpNo", woReqData.get("REQ_EMP_NO")==null?"":woReqData.get("REQ_EMP_NO").toString());
                            sendData.put("SendEmpName", woReqData.get("REQ_EMP_NAME")==null?"":woReqData.get("REQ_EMP_NAME").toString());
                            sendData.put("RecEmpNo", empMap.get("EMP_NO")==null?"":empMap.get("EMP_NO").toString());
                            sendData.put("RecEmpName", empMap.get("EMP_NAME")==null?"":empMap.get("EMP_NAME").toString());
                            sendData.put("MessageObjectType", "REQ10");
                            sendData.put("ObjectId", objectId);
                            sendData.put("ObjectNo", woReqData.get("WOREQ_NO")==null?"":woReqData.get("WOREQ_NO").toString());
                            sendData.put("Senders", woReqData.get("REQ_PHONE")==null?"":woReqData.get("REQ_PHONE").toString());
                            sendData.put("Description", "");
                            sendData.put("Receivers", phoneNumber);
                            sendData.put("Contents", contents);
                            sendData.put("MethodType", "KAKAO_ALARM");
                            sendData.put("KeyValue", authData.get("KEY_VALUE")==null?"":authData.get("KEY_VALUE").toString());
                            sendData.put("PageUrl", MwareConfig.getDreamUrl()+"index.do?redirectPage=maWoReqList&redirectParam=%7B%22maWoReqCommonDTO.filterWoReqNo%22:%22"+woReqData.get("WOREQ_NO").toString()+"%22,%22isLinked%22:%22Y%22%7D");
                            sendData.put("Lang", user.getLocale().toString());
                            
                            //알람 보내기 
                            String[] resultData = sendKakaoAlarm(sendData);
                           
                            //결과 받아서 로그 업데이트해야함.
                            MgrMessageTransDetailDTO mgrMessageTransDetailDTO = new MgrMessageTransDetailDTO();
                            mgrMessageTransDetailDTO.setMessageId(messageListId);
                            mgrMessageTransDetailDTO.setMsgStatusId("SUCCESS".equals(resultData[0])?"Y":"N");
                            mgrMessageTransDetailDTO.setFailMsg(resultData[1]);
                            mgrMessageTransDetailDAO.updateResultLog(mgrMessageTransDetailDTO, user);
                        }
                    }
                    rtn = "1";
        		}else {
                    rtn = "2";
                }
        	}
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //작업불가,접수시 요청자에게 전송
    @Override
    public String RQC10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0271");
                Map data = (Map) mgrMessageTransDetailDAO.getDataRQC10(objectId, user).get(0);
                if("REC".equals(data.get("STATUS"))){
                    title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MGS0238");
                }
                else if("INC".equals(data.get("STATUS"))){
                    title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0195");
                }
                String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("REQ_EMP_ID"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //접수자 변경 시 변경된 접수자에게 전송
    @Override
    public String RQC20(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0197");
                Map data = (Map) mgrMessageTransDetailDAO.getDataRQC10(objectId, user).get(0);
                String contents = new HtmlReporter.Builder(getFile("RQC10", user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("WOREQ_NO")==null?"":data.get("WOREQ_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("REC_EMP_ID"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //작업완료시 요청자에게 전송
    @Override
    public String WRK10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0198");
                Map data = (Map) mgrMessageTransDetailDAO.getDataWRK10(objectId, user).get(0);
                String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("WO_NO")==null?"":data.get("WO_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("WKOR_ID", data.get("WKOR_ID"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //작업완료시 작업부서 전원에게 전송
    @Override
    public String WRK20(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
              String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0198");
              Map data = (Map) mgrMessageTransDetailDAO.getDataWRK10(objectId, user).get(0);
              String contents = new HtmlReporter.Builder(getFile("WRK10", user))
                      .data(data)
                      .locale(user.getLocale())
                      .build();
              String sendEmpNo = user.getEmpNo();
              String objNo = data.get("WO_NO")==null?"":data.get("WO_NO").toString();
              
              Map empParams = new HashMap();
              empParams.put("COMP_NO", data.get("COMP_NO"));
              empParams.put("DEPT_ID", data.get("DEPT_ID"));
              
              List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
              for(Object obj:empList.toArray()) {
                  Map empMap = (Map) obj;
                  if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                      String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                      String recEmpNo = empMap.get("EMP_NO").toString();
                      
                      insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                  }
              }
              rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //현장구매신청 시 구매 담당자에게 전송
    @Override
    public String PPR10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG260");
                Map data = (Map) mgrMessageTransDetailDAO.getDataPPR10(objectId, user).get(0);
                String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("PTPNLISTNO")==null?"":data.get("PTPNLISTNO").toString();
                
                List empList = new ArrayList();
                
                Map empParams = new HashMap();
                String recBy = data.get("REC_BY")==null?"":data.get("REC_BY").toString();
                String recDept = data.get("REC_DEPT")==null?"":data.get("REC_DEPT").toString();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                if(!"".equals(recBy)) {
                    empParams.put("EMP_ID", recBy);
                    empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                }
                else if(!"".equals(recDept)) {
                    empParams.put("DEPT_ID", recDept);
                    empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                }
                
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //구매신청시 발주담당자에게 전송
    @Override
    public String PRI10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG250");
                Map data = (Map) mgrMessageTransDetailDAO.getDataPRI10(objectId, user).get(0);
                List subList = mgrMessageTransDetailDAO.getSubListPRI10(objectId, user);
                String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                        .data(data)
                        .addList(subList)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("PTPRLISTNO")==null?"":data.get("PTPRLISTNO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("REC_BY"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //출고처리시 수령자에게 전송
    @Override
    public String ISS10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0239");
                Map data = (Map) mgrMessageTransDetailDAO.getDataISS10(objectId, user).get(0);
                String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("PTISSLIST_NO")==null?"":data.get("PTISSLIST_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("REC_BY"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //QNA완료시 요청자에게 전송
    @Override
    public String QNA20(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0240");
                Map data = (Map) mgrMessageTransDetailDAO.getDataQNA20(objectId, user).get(0);
                String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("HELPDESK_NO")==null?"":data.get("HELPDESK_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("REQ_BY"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //오류발생시 담당자에게 전송
    @Override
    public String ERR10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String receiver = MwareConfig.getManagerMailId();
                if(!"".equals(receiver)) {
                    String title = mgrMessageTransDetailDAO.findMailTitle(user.getCompNo(), objectId);
                    Map data = (Map) mgrMessageTransDetailDAO.findErrorLog(objectId, user).get(0);
                    String contents = new HtmlReporter.Builder(getFile(messageObjectType, user))
                            .data(data)
                            .locale(user.getLocale())
                            .build();
                    
                    String sendEmpNo = user.getEmpNo();
                    String recEmpNo = "ADMIN";
                    String objNo = objectId;
                    
                    insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    
                    rtn = "1";
                }
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //사용자계정 등록시 사용자에게 전송
    @Override
    public String USR10(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0242");
                Map data = (Map) mgrMessageTransDetailDAO.getDataUSR10(objectId, user).get(0);
                String format = "USR10";
                if("Y".equals(MwareConfig.getIsUseOtpLogin())){
                    format = "USR10_OTP";
                    String subTitle = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0280");
                    data.put("SUB_TITLE", subTitle);
                }
                String contents = new HtmlReporter.Builder(getFile(format, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("USER_NO")==null?"":data.get("USER_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("EMP_ID"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    //사용자 비밀번호 리셋시 사용자에게 전송
    @Override
    public String USR20(String objectId, String methodType, User user) throws SqlIgnoreException
    {
        //1:성공, 2:메일서비스 비활성화 상태
        String rtn = "-1";
        try{
            String messageObjectType = new Object(){}.getClass().getEnclosingMethod().getName();
            
            if("Y".equals(isUseMessageCateg(messageObjectType, methodType, user.getCompNo()))) {
                String title = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0256");
                Map data = (Map) mgrMessageTransDetailDAO.getDataUSR20(objectId, user).get(0);
                String format = "USR20";
                if("Y".equals(MwareConfig.getIsUseOtpLogin())){
                    format = "USR20_OTP";
                    String subTitle = MessageUtil.getMessage(user.getLocale(), "MESSAGE", "MSG0280");
                    data.put("SUB_TITLE", subTitle);
                }
                //decode password
                String password = data.get("PASSWORD")==null?"":data.get("PASSWORD").toString();
                password = CommonUtil.aesDecodeString(password);
                data.put("PASSWORD", password);
                
                String contents = new HtmlReporter.Builder(getFile(format, user))
                        .data(data)
                        .locale(user.getLocale())
                        .build();
                String sendEmpNo = user.getEmpNo();
                String objNo = data.get("USER_NO")==null?"":data.get("USER_NO").toString();
                
                Map empParams = new HashMap();
                empParams.put("COMP_NO", data.get("COMP_NO"));
                empParams.put("EMP_ID", data.get("EMP_ID"));
                
                List empList = mgrMessageTransDetailDAO.getEmp(empParams, user);
                for(Object obj:empList.toArray()) {
                    Map empMap = (Map) obj;
                    if("Y".equals(isUseMessageEmp(messageObjectType, methodType, empMap.get("COMP_NO").toString(), empMap.get("EMP_ID").toString()))) {
                        String receiver = empMap.get("E_MAIL")==null?"":empMap.get("E_MAIL").toString();
                        String recEmpNo = empMap.get("EMP_NO").toString();
                        
                        insertDetail(receiver, contents, title, user, sendEmpNo ,recEmpNo, messageObjectType, methodType, objectId, objNo);
                    }
                }
                rtn = "1";
            }
            else {
                rtn = "2";
            }
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
        
        return rtn;
    }
    @Override
    public String isUseMessageCateg(String messageObjectType, String methodType, String compNo) throws Exception
    {
        String rtn = "";
        if("MAIL".equals(methodType)) {
            rtn = mgrMessageTransDetailDAO.isUseMailCateg(messageObjectType, compNo);
        }
        else if("SMS".equals(methodType)) {
            rtn = mgrMessageTransDetailDAO.isUseSmsCateg(messageObjectType, compNo);
        }
        else if("KAKAO_ALARM".equals(methodType)) {
            rtn = mgrMessageTransDetailDAO.isUseKakaoCateg(messageObjectType, compNo);
        }
        return rtn;
    }
    @Override
    public String isUseMessageEmp(String messageObjectType, String methodType, String compNo, String empId) throws Exception
    {
        String rtn = "";
        if("MAIL".equals(methodType)) {
            rtn = mgrMessageTransDetailDAO.isUseMailEmp(messageObjectType, compNo, empId);
        }
        else if("SMS".equals(methodType)) {
            rtn = mgrMessageTransDetailDAO.isUseSmsEmp(messageObjectType, compNo, empId);
        }
        else if("KAKAO_ALARM".equals(methodType)) {
            rtn = mgrMessageTransDetailDAO.isUseKakaoEmp(messageObjectType, compNo, empId);
        }
        return rtn;
    }
    @Override
    public List getEmp(Map map, User user) throws Exception
    {
        return mgrMessageTransDetailDAO.getEmp(map, user);
    }

    private String[] sendKakaoAlarm(Map data){
		String[] returnData = new String[2];
		//http call
		try {
			URL url = new URL(MwareConfig.getMessageServiceUrl());
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			con.setDoOutput(true);
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			Gson gson = new Gson();
			 
			Map<String, Object> contentsMap = new HashMap();
			contentsMap.put("serviceName", "SEND_KAKAO_ALARM");
			contentsMap.put("data", gson.toJson(data));

			StringBuilder postData = new StringBuilder();
	        for(Map.Entry<String,Object> param : contentsMap.entrySet()) {
	            if(postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
	        
	        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

			wr.write(postDataBytes);
			
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8")); 
			String inputLine; 
			StringBuffer response = new StringBuffer(); 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine); 
				} 
			in.close(); // print result 
			
			if(responseCode == 200){
				returnData[0] = "SUCCESS";
				returnData[1] = response.toString();
			}else{
				returnData[0] = "ERROR";
				returnData[1] = response.toString();
			}
		} catch (MalformedURLException e) {
			returnData[0] = "ERROR MalformedURL";
			returnData[1] = e.getMessage();
		}catch (IOException e) {
			returnData[0] = "ERROR IO";
			returnData[1] = e.getMessage();
		}catch (Exception e) {
			returnData[0] = "ERROR ETC";
			returnData[1] = e.getMessage();
		}
		return returnData;
	}
    
    private String addCountryCode(String countryCode, String phoneNumber){
    	String result = "";
    	if(countryCode == null || phoneNumber == null || "".equals(countryCode) || "".equals(phoneNumber)) return "";
    	if(countryCode.indexOf("+82")>=0) phoneNumber = phoneNumber.substring(1,phoneNumber.length());
    	if(countryCode.contains("+")) countryCode = countryCode.replaceAll("\\+", "");
    	result = (countryCode + phoneNumber).replaceAll("-", "");
    	
    	return result;
    }
}
