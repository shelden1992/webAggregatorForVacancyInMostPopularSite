package net.servletDatabase.modelDao;

import com.aggregator.Controller;
import com.aggregator.model.*;
import com.aggregator.view.DatabaseView;
import com.aggregator.view.View;
import net.servletDatabase.inject.Inject;

public class GoToAggregVacationAndCreateNewDatabase {

    public void aggregatorCreate(String typeVacanсy, String city, String nameDatabase, boolean newDatabase) {


        Provider[] providers={new Provider(new RabotaStrategy()), new Provider(new DouStrategy()), new Provider(new HHStrategy()), new Provider(new WorkUaStrategy())};


//        HtmlView htmlView = new HtmlView();


//        @Inject("view")
        View databaseView=new DatabaseView();
        View[] views={databaseView};

        Controller controller=new Controller(new Model(views, providers));


        controller.onCitySelectAndTypeVacancy(typeVacanсy, city, nameDatabase, newDatabase);

        databaseView.setController(controller);
    }
}
