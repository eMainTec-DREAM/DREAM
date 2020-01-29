package intf.dream.android.online.woreq.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import intf.dream.android.online.woreq.form.AnOnWoReqListForm;
import intf.dream.android.online.woreq.service.AnOnWoReqListService;

/**
 * 온라인버전 작업요청/접수
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/anOnWoReqList" name="anOnWoReqListForm"
 *                input="/android/online/woreq/anOnWoReqList.jsp"
 *                scope="request" validate="false"
 */
public class AnOnWoReqListAction extends IfOnlineAuthAction {
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
		AnOnWoReqListForm anOnWoReqListForm = (AnOnWoReqListForm) form;

		switch (anOnWoReqListForm.getServiceName()) {
		case AnOnWoReqListAction.WOREQ_LIST:
			findWoReqList(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WOREQ_DELETE:
			deleteWoReq(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WOREQ_INSERT:
			insertWoReq(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WOREQ_UPDATE:
			updateWoReq(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnOnWoReqListAction.WORES_LIST:
			findWoResList(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WORES_UPDATE:
			updateWoRes(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WORES_DELETE:
			deleteWoRes(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnOnWoReqListAction.WOREQRES_LIST:
			findWoReqResList(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WOREQRES_INSERT:
			insertWoReqRes(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WOREQRES_UPDATE:
			updateWoReqRes(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.WOREQRES_DELETE:
			deleteWoReqRes(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case AnOnWoReqListAction.PHOTO_LIST:
			findPhotoList(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.PHOTO_INSERT:
			insertPhoto(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.PHOTO_UPDATE:
			updatePhoto(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.PHOTO_DELETE:
			deletePhoto(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case AnOnWoReqListAction.TEMP_PHOTO_DELETE:
			deleteTempPhoto(request, response, anOnWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findWoReqList(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anOnWoReqListService.findWoReqList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void deleteWoReq(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.deleteWoReq(list);
		setMessage(response, "", "OK");
	}

	private void insertWoReq(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService", request);
		List list = getListData(request);
		int qty = anOnWoReqListService.insertWoReq(list);
		setMessage(response, "", "OK");
	}

	private void updateWoReq(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.updateWoReq(list);

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
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anOnWoReqListService.findWoResList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void updateWoRes(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
    	AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService", request);
    	List list = getListData(request);
    	
        int qty = anOnWoReqListService.updateWoRes(list);
    	
    	setMessage(response, "","OK");
    }

	private void deleteWoRes(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.deleteWoRes(list);
		setMessage(response, "", "OK");
	}

	private void findWoReqResList(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anOnWoReqListService.findWoReqResList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void insertWoReqRes(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService", request);
		List list = getListData(request);
		int qty = anOnWoReqListService.insertWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void updateWoReqRes(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.updateWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void deleteWoReqRes(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.deleteWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void findPhotoList(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = anOnWoReqListService.findPhotoList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void insertPhoto(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		FormFile[] fileList = anOnWoReqListForm.getFileList();

		anOnWoReqListService.insertPhoto(list, fileList);
		setMessage(response, "", "OK");
	}

	private void updatePhoto(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		FormFile[] fileList = anOnWoReqListForm.getFileList();
		int qty = anOnWoReqListService.updatePhoto(list, fileList);
		setMessage(response, "", "OK");
	}

	private void deletePhoto(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.deletePhoto(list);
		setMessage(response, "", "OK");
	}

	private void deleteTempPhoto(HttpServletRequest request, HttpServletResponse response,
			AnOnWoReqListForm anOnWoReqListForm) throws Exception {
		AnOnWoReqListService anOnWoReqListService = (AnOnWoReqListService) getBean("anOnWoReqListService");
		List list = getListData(request);
		int qty = anOnWoReqListService.deleteTempPhoto(list);
		setMessage(response, "", "OK");
	}
}
