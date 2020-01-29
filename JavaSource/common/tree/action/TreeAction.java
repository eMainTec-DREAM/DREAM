package common.tree.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.struts.AuthAction;
import common.tree.form.TreeForm;
import common.tree.service.TreeService;

/**
 * Tree 구현 샘플 소스이다.
 * 
 * @struts:action path="/tree" name="treeForm" scope="request" validate="false"
 * @struts.action-forward name="success" path="/common/tree/bottomTree.jsp"
 *                        redirect="false"
 * @author javaworker
 * @version CVS $Id: TreeAction.java,v 1.4 2014/02/11 00:22:37 pochul2423 Exp $
 * @since 2005. 5. 10.
 */
public class TreeAction
        extends AuthAction
{    
    // Tree 종류
    public static final String LOCATTREE = "LOCATION";  // 기능위치(설비분류Tree)
    public static final String LOC_CODE_TREE = "LOC_CODE";  // 위치(설비분류Tree)
    public static final String CATEGTREE = "EQ_CATEGORY";  // 계통(설비분류Tree)
    public static final String EQTYPTREE = "EQ_TYPE";   // 종류(설비분류Tree)
    public static final String RTTAGTREE = "REDTAG_GROUP";   // 차단기그룹
    public static final String REDTAGLOC = "REDTAG_LOC";   // 차단기 위치
    public static final String HSEDOC  = "HSE_DOC";   // HSE Doc
    
    public static final String DEPT_TREE = "DEPART";    // 부서
    public static final String DIR_SYMPTOM = "SYMPTOM"; // 현상
    
    public static final String CLASSTREE = "CLASS"; //자재 Class
    public static final String UOMTREE  = "UOM"; // 단위
    
    public static final String PROJECT = "PROJECT";   // 공사Tree
    
    public static final String CALTREE  = "CALENDAR";   // 년,월
    public static final String IS_USE  = "IS_USE";   // 사용여부(Y,N)
    public static final String P_MENU  = "P_MENU";   // 상위메뉴
    public static final String PAGE  = "PAGE";   // 화면
    public static final String MENU  = "MENU";   // 메뉴
    public static final String KEY_TYPE  = "KEY_TYPE";   // 등록유형(다국어)
    public static final String EQ_STATUS  = "EQ_STATUS";   // 설비상태
    public static final String BUTTON  = "BUTTON";   // 버튼
    
    /** Import Data 위치 */
    public static final String IMP_LOCATTREE = "IMP_LOCATION";  // 기능위치(설비분류Tree)

    protected ActionForward run(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {        
        TreeForm    treeForm = null;
        TreeService treeService = null;
        
        // 조회할 parent node의 이하 바로 밑의 folder 와 item을 조회한다.
        String[][] folders = null;
        String[][] items = null;
        
        // tag library 에서 submit 시에 전송되는 parameter 값이 자동으로 셋팅된다.
        treeForm    = (TreeForm) form;
        // sampleService를 생성하여 리턴한다.
        treeService = (TreeService) getBean("treeService");
        
        // =============================================
        // 검색할 parent node의 정보이다.
        String parentNode = treeForm.getParentNode();
        String treeType   = treeForm.getTreeType();
        // =============================================
    
        // 서비스를 실행하고[business logic]결과를 ArrayList 리턴받는다.
        folders = treeService.findTreeFolder(parentNode, treeType);
        items   = treeService.findTreeItem(parentNode, treeType);

        /*
         * For Test : set Sample DATA DB에서 조회된 값이 2차원 배열로 folder 와 item 전송된다.
         * String [][0] : TREE 에 보여지는 이름 String [][1] : TREE 의 속성 값 (code,
         * action값 등)
         */
        treeForm.setFolders(folders);
        treeForm.setItems(items);

        return mapping.findForward("success");
    }
}
