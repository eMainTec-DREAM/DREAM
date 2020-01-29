package intf.dream.cricket.woreq.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import intf.dream.cricket.woreq.form.CricketWoReqListForm;
import intf.dream.cricket.woreq.service.CricketWoReqListService;

/**
 * 온라인버전 작업요청/접수
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/cricketWoReqList" name="cricketWoReqListForm"
 *                input="/cricket/woreq/cricketWoReqList.jsp"
 *                scope="request" validate="false"
 */
public class CricketWoReqListAction extends IfOnlineAuthAction {
	// WOREQ LIST
	public static final String WOREQ_LIST = "WOREQ_LIST";
	// WOREQ DELETE
	public static final String WOREQ_DELETE = "WOREQ_DELETE";
	// WOREQ INSERT
	public static final String WOREQ_INSERT = "WOREQ_INSERT";
	// WOREQ UPDATE
	public static final String WOREQ_UPDATE = "WOREQ_UPDATE";

	// WORES LIST
	public static final String WORES_LIST = "WORES_LIST";
	// WORES UPDATE
	public static final String WORES_UPDATE = "WORES_UPDATE";
	// WORES DELETE
	public static final String WORES_DELETE = "WORES_DELETE";

	// WOREQRES LIST
	public static final String WOREQRES_LIST = "WOREQRES_LIST";
	// WOREQRES INSERT
	public static final String WOREQRES_INSERT = "WOREQRES_INSERT";
	// WOREQRES UPDATE
	public static final String WOREQRES_UPDATE = "WOREQRES_UPDATE";
	// WOREQRES DELETE
	public static final String WOREQRES_DELETE = "WOREQRES_DELETE";

	// PHOTO LIST
	public static final String PHOTO_LIST = "PHOTO_LIST";
	// PHOTO INSERT
	public static final String PHOTO_INSERT = "PHOTO_INSERT";
	// PHOTO UPDATE
	public static final String PHOTO_UPDATE = "PHOTO_UPDATE";
	// PHOTO DELETE
	public static final String PHOTO_DELETE = "PHOTO_DELETE";
	// TEMP PHOTO DELETE
	public static final String TEMP_PHOTO_DELETE = "TEMP_PHOTO_DELETE";

	protected ActionForward run(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward returnActionForward = null;
		CricketWoReqListForm cricketWoReqListForm = (CricketWoReqListForm) form;

		switch (cricketWoReqListForm.getServiceName()) {
		case CricketWoReqListAction.WOREQ_LIST:
			findWoReqList(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WOREQ_DELETE:
			deleteWoReq(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WOREQ_INSERT:
			insertWoReq(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WOREQ_UPDATE:
			updateWoReq(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case CricketWoReqListAction.WORES_LIST:
			findWoResList(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WORES_UPDATE:
			updateWoRes(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WORES_DELETE:
			deleteWoRes(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case CricketWoReqListAction.WOREQRES_LIST:
			findWoReqResList(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WOREQRES_INSERT:
			insertWoReqRes(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WOREQRES_UPDATE:
			updateWoReqRes(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.WOREQRES_DELETE:
			deleteWoReqRes(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case CricketWoReqListAction.PHOTO_LIST:
			findPhotoList(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.PHOTO_INSERT:
			insertPhoto(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.PHOTO_UPDATE:
			updatePhoto(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.PHOTO_DELETE:
			deletePhoto(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case CricketWoReqListAction.TEMP_PHOTO_DELETE:
			deleteTempPhoto(request, response, cricketWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findWoReqList(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = cricketWoReqListService.findWoReqList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void deleteWoReq(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.deleteWoReq(list);
		setMessage(response, "", "OK");
	}

	private void insertWoReq(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService", request);
		List list = getListData(request);
		int qty = cricketWoReqListService.insertWoReq(list);
		setMessage(response, "", "OK");
	}

	private void updateWoReq(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.updateWoReq(list);

		// PUSH 테스트

		// final String apiKey =
		// "AAAAF7-jlzM:APA91bG79FZ-HSIYFeAsoCryqD18uV-C10o4zSafARf1czSWMODfC2mdVXfxs9CCCOw1LJL-xFSitN3r16psS7g1CBWjTJGKJy4Kg4RmA0f8jUu8gL_AWQ7MIUdJY3a2dikFyE8bd8Nn";
		// URL url = new URL("https://fcm.googleapis.com/fcm/send");
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setDoOutput(true);
		// conn.setRequestMethod("POST");
		// conn.setRequestProperty("Content-Type", "application/json");
		// conn.setRequestProperty("Authorization", "key=" + apiKey);
		//
		// conn.setDoOutput(true);
		//
		// String userId =(String)
		// request.getSession().getAttribute("ssUserId");
		//
		// // 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
		// String input = "{\"notification\" : {\"title\" : \" 여기다 제목넣기 \",
		// \"body\" : \"여기다 내용 넣기\"},
		// \"to\":\"cRYgmIikUik:APA91bHYFdCWBrtQVv-Sl5CQy-uWvF8YiOScjgA2on0djdUYV6WlcalMqygs5ey7oDH90u02HNYVtbZ5HxVRTIcWnHrwR-EPRjrjh2FcynbVDeg_Nz3YsDyclx3-sDQIag65UxRhGdwL\"}";
		//
		// OutputStream os = conn.getOutputStream();
		//
		// // 서버에서 날려서 한글 깨지는 사람은 아래처럼 UTF-8로 인코딩해서 날려주자
		// os.write(input.getBytes("UTF-8"));
		// os.flush();
		// os.close();
		//
		// int responseCode = conn.getResponseCode();
		// System.out.println("\nSending 'POST' request to URL : " + url);
		// System.out.println("Post parameters : " + input);
		// System.out.println("Response Code : " + responseCode);
		//
		// BufferedReader in = new BufferedReader(new
		// InputStreamReader(conn.getInputStream()));
		// String inputLine;
		// StringBuffer response1 = new StringBuffer();
		//
		// while ((inputLine = in.readLine()) != null) {
		// response1.append(inputLine);
		// }
		// in.close();
		// // print result
		// System.out.println(response1.toString());

		setMessage(response, "", "OK");
	}

	private void findWoResList(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = cricketWoReqListService.findWoResList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void updateWoRes(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
    	CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService", request);
    	List list = getListData(request);
        
        int qty = cricketWoReqListService.updateWoRes(list);
    	
    	setMessage(response, "","OK");
    }

	private void deleteWoRes(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.deleteWoRes(list);
		setMessage(response, "", "OK");
	}

	private void findWoReqResList(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = cricketWoReqListService.findWoReqResList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void insertWoReqRes(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService", request);
		List list = getListData(request);
		int qty = cricketWoReqListService.insertWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void updateWoReqRes(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.updateWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void deleteWoReqRes(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.deleteWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void findPhotoList(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = cricketWoReqListService.findPhotoList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void insertPhoto(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		FormFile[] fileList = cricketWoReqListForm.getFileList();

		cricketWoReqListService.insertPhoto(list, fileList);
		setMessage(response, "", "OK");
	}

	private void updatePhoto(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		FormFile[] fileList = cricketWoReqListForm.getFileList();
		int qty = cricketWoReqListService.updatePhoto(list, fileList);
		setMessage(response, "", "OK");
	}

	private void deletePhoto(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.deletePhoto(list);
		setMessage(response, "", "OK");
	}

	private void deleteTempPhoto(HttpServletRequest request, HttpServletResponse response,
			CricketWoReqListForm cricketWoReqListForm) throws Exception {
		CricketWoReqListService cricketWoReqListService = (CricketWoReqListService) getBean("cricketWoReqListService");
		List list = getListData(request);
		int qty = cricketWoReqListService.deleteTempPhoto(list);
		setMessage(response, "", "OK");
	}
}
