package common.finder.valid.service;

import java.util.List;

import org.codehaus.jettison.json.JSONException;
import org.json.simple.parser.ParseException;

import common.bean.User;
import common.finder.valid.dto.ListOfValDTO;
import common.finder.valid.form.ListOfValForm;

/**
 * List Of Value Service
 * @author  javaworker
 * @version $Id: ListOfValService.java,v 1.2 2014/01/28 07:49:27 pochul2423 Exp $
 * @since   1.0
 *
 */
public interface ListOfValService
{

    /**
     * code, description 을 검색 조건으로 
     * 유저코드를 검색한다.
     * @author  javaworker
     * @version $Id: ListOfValService.java,v 1.2 2014/01/28 07:49:27 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    List findUsrDirList(ListOfValDTO listOfValDTO);
    /**
     * code, description 을 검색 조건으로 
     * 시스템코드를 검색한다.
     * @author  javaworker
     * @version $Id: ListOfValService.java,v 1.2 2014/01/28 07:49:27 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     * @throws ParseException 
     * @throws JSONException 
     */
    List findSysDirList(ListOfValDTO listOfValDTO,User user) throws ParseException, JSONException;
    /**
     * code, description 을 검색 조건으로 
     * 회사코드를 검색한다.
     * @author  javaworker
     * @version $Id: ListOfValService.java,v 1.2 2014/01/28 07:49:27 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     * @throws ParseException 
     * @throws JSONException 
     */
    List findCompList(ListOfValDTO listOfValDTO,User user) throws ParseException, JSONException;

    /**
     * code, description 을 검색 조건으로 
     * 별도의 Table 을 검색한다.
     * @author  javaworker
     * @version $Id: ListOfValService.java,v 1.2 2014/01/28 07:49:27 pochul2423 Exp $
     * @since   1.0
     * 
     * @param listOfValDTO
     * @return
     */
    List findTableList(ListOfValDTO listOfValDTO, User user);
	/**
	 * Json Setting 
	 * @param listOfValService
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws ParseException 
	 */
	ListOfValDTO setJsonParm(ListOfValDTO listOfValDTO) throws ClassNotFoundException, ParseException;
	/**
	 * Ac LOV
	 * @param listOfValDTO
	 * @param user
	 * @param listOfValForm
	 * @return
	 */
	List findAcSysDirList(ListOfValDTO listOfValDTO, User user, ListOfValForm listOfValForm);
}