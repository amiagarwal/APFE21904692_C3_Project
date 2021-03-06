import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    RestaurantService service = new RestaurantService();
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    private void createMockRestaurant() {
        LocalTime openingTime = LocalTime.parse("09:00:00");
        LocalTime closingTime = LocalTime.parse("23:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie", 319);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime restaurantCurrentTime = LocalTime.parse("10:00:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant) ;
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(restaurantCurrentTime) ;
        assertTrue(spiedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime restaurantCurrentTime = LocalTime.parse("08:00:00");
        Restaurant spiedRestaurant = Mockito.spy(restaurant) ;
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(restaurantCurrentTime) ;
        assertFalse(spiedRestaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<<<<<ORDER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void get_order_value_should_return_388_if_first_two_items_worth_388_selected_from_menu() {
        // Arrange
        String[] selectedItems = new String[] { "Sweet corn soup", "Vegetable lasagne" };
        int expectedOrderValue = 388;

        // Act
        int orderValue = restaurant.getOrderValue(selectedItems);

        // Assert
        assertEquals(expectedOrderValue, orderValue);
    }

    @Test
    public void get_order_value_should_return_0_if_no_items_selected_from_menu() {
        // Arrange
        String[] selectedItems = new String[] {};
        int expectedOrderValue = 0;

        // Act
        int orderValue = restaurant.getOrderValue(selectedItems);

        // Assert
        assertEquals(expectedOrderValue, orderValue);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<ORDER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}