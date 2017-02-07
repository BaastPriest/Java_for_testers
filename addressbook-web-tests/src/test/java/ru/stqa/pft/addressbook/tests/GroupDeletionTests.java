package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion () { //переименовала метод с маленькой буквы
        app.gotoGroupPage();
        app.groupHelper.selectGroup();
        app.groupHelper.deleteSelectedGroups();
        app.groupHelper.returnToGroupPage();
    }

}
