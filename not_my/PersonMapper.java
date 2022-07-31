package ru.medexpert.server.db.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ru.medexpert.shared.beans.PersonHeavyBean;
import ru.medexpert.shared.beans.PersonLiteBean;
import ru.medexpert.shared.beans.PersonSuggestBean;
import ru.medexpert.shared.beans.PersonSuggestSearchBean;
import ru.medexpert.shared.beans.WorkNodeBean;
import ru.medexpert.shared.beans.search.FilterSearchBean;

public interface PersonMapper {

    PersonHeavyBean getPersonHeavyBean(@Param("personId") int personId, @Param("visitId") Integer visitId);

    void insertPerson(PersonHeavyBean bean);

    void updatePerson(PersonHeavyBean bean);

    void updatePersonHash(@Param("personId") Integer personId, @Param("hashLocal") String hashLocal, @Param("hashEng") String hashEng);

    void updateOldPersonTrack(@Param("personId") Integer personId);

    void insertPersonTrack(@Param("personOld") PersonHeavyBean personOld, @Param("searchPattern") String searchPattern,
            @Param("personNew") PersonHeavyBean personNew,
            @Param("staffId") Integer staffId, @Param("staffRoleId") Integer staffRoleId,
            @Param("workNode") WorkNodeBean workNode);

    Integer getLastPersonTrackIdByPersonId(@Param("personId") Integer personId);

    List<PersonSuggestBean> getSuggestReceptionPersonPatient(@Param("searchBean") PersonSuggestSearchBean searchBean,
            @Param("searchCondition") String searchCondition);

    List<PersonSuggestBean> getPersonNameHistory(Integer personId);

    List<PersonSuggestBean> getSuggestStaff(@Param("searchBean") PersonSuggestSearchBean searchBean, @Param("searchCondition") String searchCondition);

    List<PersonSuggestBean> getSuggestPerson(@Param("searchBean") PersonSuggestSearchBean searchBean, @Param("searchCondition") String searchCondition);

    PersonSuggestBean getPersonSuggestByPersonId(@Param("personId") Integer personId);

    List<PersonSuggestBean> getSuggestPatient(@Param("searchBean") PersonSuggestSearchBean searchBean, @Param("searchCondition") String searchCondition);

    boolean checkPersonPhone(@Param("personId") Integer personId, @Param("phone") String phone);

    List<PersonSuggestBean> getPatientsForControlPlus(@Param("searchBean") PersonSuggestSearchBean searchBean, @Param("searchCondition") String searchCondition,
            @Param("filterSearchBean") FilterSearchBean filterSearchBean, @Param("rangeFrom") int rangeFrom, @Param("rangeTo") int rangeTo);

    PersonLiteBean getPersonFIO(@Param("personId") int personId);
}
