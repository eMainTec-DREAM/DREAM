package intf.dream.bee.woreq.action;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import common.struts.IfOnlineAuthAction;
import common.util.CommonUtil;
import intf.dream.bee.woreq.dto.BeeWoReqCommonDTO;
import intf.dream.bee.woreq.form.BeeWoReqListForm;
import intf.dream.bee.woreq.service.BeeWoReqListService;

/**
 * 온라인버전 작업요청/접수
 * 
 * @author
 * @version $Id: $
 * @since 1.0
 * @struts:action path="/beeWoReqList" name="beeWoReqListForm"
 *                input="/bee/woreq/beeWoReqList.jsp"
 *                scope="request" validate="false"
 */
public class BeeWoReqListAction extends IfOnlineAuthAction {
	// WOREQ LIST
	public static final String WOREQ_LIST = "WOREQ_LIST";
	// WOREQ LIST COUNT
	public static final String WOREQ_COUNT = "WOREQ_COUNT";
	// WOREQ DELETE
	public static final String WOREQ_DELETE = "WOREQ_DELETE";
	// WOREQ INSERT
	public static final String WOREQ_INSERT = "WOREQ_INSERT";
	// WOREQ UPDATE
	public static final String WOREQ_UPDATE = "WOREQ_UPDATE";

	// WORES LIST
	public static final String WORES_LIST = "WORES_LIST";
	// WORES LIST COUNT
	public static final String WORES_COUNT = "WORES_COUNT";
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
		BeeWoReqListForm beeWoReqListForm = (BeeWoReqListForm) form;

		switch (beeWoReqListForm.getServiceName()) {
		case BeeWoReqListAction.WOREQ_LIST:
			findWoReqList(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQ_COUNT:
			findWoReqCount(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQ_DELETE:
			deleteWoReq(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQ_INSERT:
			insertWoReq(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQ_UPDATE:
			updateWoReq(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case BeeWoReqListAction.WORES_LIST:
			findWoResList(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WORES_COUNT:
			findWoResCount(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WORES_UPDATE:
			updateWoRes(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WORES_DELETE:
			deleteWoRes(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case BeeWoReqListAction.WOREQRES_LIST:
			findWoReqResList(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQRES_INSERT:
			insertWoReqRes(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQRES_UPDATE:
			updateWoReqRes(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.WOREQRES_DELETE:
			deleteWoReqRes(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		case BeeWoReqListAction.PHOTO_LIST:
			findPhotoList(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.PHOTO_INSERT:
			insertPhoto(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.PHOTO_UPDATE:
			updatePhoto(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.PHOTO_DELETE:
			deletePhoto(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;
		case BeeWoReqListAction.TEMP_PHOTO_DELETE:
			deleteTempPhoto(request, response, beeWoReqListForm);
			returnActionForward = mapping.findForward("jsonPage");
			break;

		default:
			returnActionForward = mapping.findForward("jsonPage");
			break;
		}

		return returnActionForward;
	}

	private void findWoReqList(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		BeeWoReqCommonDTO beeWoReqCommonDTO = beeWoReqListForm.getBeeWoReqCommonDTO();
		Map map = getMapData(request);
		
		beeWoReqCommonDTO.setIsLoadMaxCount(true);
		if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
			beeWoReqCommonDTO.setIsLoadMaxCount(false);
		}
		beeWoReqCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));

		// 리스트를 조회한다.
		List resultList = beeWoReqListService.findWoReqList(beeWoReqCommonDTO, map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void findWoReqCount(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		BeeWoReqCommonDTO beeWoReqCommonDTO = beeWoReqListForm.getBeeWoReqCommonDTO();
		Map map = getMapData(request);
		
		// 리스트를 조회한다.
		List resultList = beeWoReqListService.findWoReqCount(beeWoReqCommonDTO, map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}
	private void deleteWoReq(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.deleteWoReq(list);
		setMessage(response, "", "OK");
	}

	private void insertWoReq(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService", request);
		List list = getListData(request);
		int qty = beeWoReqListService.insertWoReq(list);
		setMessage(response, "", "OK");
	}

	private void updateWoReq(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.updateWoReq(list);

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
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		BeeWoReqCommonDTO beeWoReqCommonDTO = beeWoReqListForm.getBeeWoReqCommonDTO();
		Map map = getMapData(request);
		
		beeWoReqCommonDTO.setIsLoadMaxCount(true);
		if("".equals(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))))){
			beeWoReqCommonDTO.setIsLoadMaxCount(false);
		}
		beeWoReqCommonDTO.setFirstRow(CommonUtil.convertString(String.valueOf(map.get("nextStartRow"))));

		// 리스트를 조회한다.
		List resultList = beeWoReqListService.findWoResList(beeWoReqCommonDTO, map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
		
	}
	private void findWoResCount(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		BeeWoReqCommonDTO beeWoReqCommonDTO = beeWoReqListForm.getBeeWoReqCommonDTO();
		Map map = getMapData(request);
		
		// 리스트를 조회한다.
		List resultList = beeWoReqListService.findWoResCount(beeWoReqCommonDTO, map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
		
	}

	private void updateWoRes(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
    	BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService", request);
    	List list = getListData(request);
        
        int qty = beeWoReqListService.updateWoRes(list);
    	
    	setMessage(response, "","OK");
    }

	private void deleteWoRes(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.deleteWoRes(list);
		setMessage(response, "", "OK");
	}

	private void findWoReqResList(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = beeWoReqListService.findWoReqResList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void insertWoReqRes(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService", request);
		List list = getListData(request);
		int qty = beeWoReqListService.insertWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void updateWoReqRes(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.updateWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void deleteWoReqRes(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.deleteWoReqRes(list);
		setMessage(response, "", "OK");
	}

	private void findPhotoList(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");

		Map map = getMapData(request);

		// 리스트를 조회한다.
		List resultList = beeWoReqListService.findPhotoList(map);

		// 조회한 List 를 form에 셋팅한다.
		super.makeJsonResult(resultList, request, response);
	}

	private void insertPhoto(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		FormFile[] fileList = beeWoReqListForm.getFileList();

		beeWoReqListService.insertPhoto(list, fileList);
		setMessage(response, "", "OK");
	}

	private void updatePhoto(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		FormFile[] fileList = beeWoReqListForm.getFileList();
		int qty = beeWoReqListService.updatePhoto(list, fileList);
		setMessage(response, "", "OK");
	}

	private void deletePhoto(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.deletePhoto(list);
		setMessage(response, "", "OK");
	}

	private void deleteTempPhoto(HttpServletRequest request, HttpServletResponse response,
			BeeWoReqListForm beeWoReqListForm) throws Exception {
		BeeWoReqListService beeWoReqListService = (BeeWoReqListService) getBean("beeWoReqListService");
		List list = getListData(request);
		int qty = beeWoReqListService.deleteTempPhoto(list);
		setMessage(response, "", "OK");
	}
}
