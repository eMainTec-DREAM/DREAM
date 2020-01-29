package dream.work.list.dao;

import java.util.List;

import common.bean.User;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;

/**
 * �۾���� - ��� dao
 * @author  kim21017
 * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaWoResultMstrListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    public List findWoResultPmiMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    public List findWoResultTrMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoBmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoCmResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    public List findWoTiResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoPmwResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoPmcResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int[] updateDeleteTagWoResultMstr(List<MaWoResultMstrDetailDTO> list, User user);
    
    public int create4wp(String id, User user);

    /**
     * ����Ʈ �ٵ�
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO );

    /**
     * ����Ʈ �۾���
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoCraftList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO ); 

    /**
     * ����Ʈ ���Ժ�ǰ
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoPartList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO );
    
    /**
     * ����Ʈ �˻��׸�
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoPointList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO );

    /**
     * ����Ʈ �۾������׸�
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoEqList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO );
    
    /**
     * ����Ʈ �۾��ʼ��˻��׸�
     * @author kim21017
     * @version $Id: MaWoResultMstrListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public List findReportWoStPointList(String id, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO );
    
    public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user, String woType);

	/**
	 * Emergency S/Part Issue
	 * @param wkorId
	 * @param compNo
	 * @return
	 */
	public int[] updateEmgPart(List<MaWoResultMstrDetailDTO> list, User user);

	/**
	 * Find Report Label
	 * @param string
	 * @param maWoResultMstrCommonDTO
	 * @return
	 */
	public List findReportWoListLabel(String string, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO);
	
    /**
     * grid find
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @return List
     */
    public List findWoPmwOvhResultMstrList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    
    /**
     * �������ε� ������
     * @author  js.lee
     * @version $Id: Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param user
     * @return
     */
    public String getData(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user);
    /**
     * �۾������ ���� üũ
     * @author  nhkim8548
     * @version $Id: Exp $
     * @since   1.0
     *
     * @param 	wkorId
     * @param 	user
     * @return 	
     */
    public String checkWoResultMstrStatus(String wkorId, User user);
    /**
     * �۾������ ����
     * @author  nhkim8548
     * @version $Id: Exp $
     * @since   1.0
     *
     * @param 	wkorId
     * @param 	user
     * @return 	
     */
    public int deleteWoResultMstr(String wkorId, User user);
    
}