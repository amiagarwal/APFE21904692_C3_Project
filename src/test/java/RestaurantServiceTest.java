import org.junit.jupiter.api.*;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
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

    //>>>>>>>>>>>>>>>>>>>>>>SEARCHING<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void searching_for_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        // Arrange
        String restaurantName = "Amelie's cafe";
        Restaurant expectedRestaurant = restaurant;

        // Act
        restaurant = service.findRestaurantByName(restaurantName);

        // Assert
        assertEquals(expectedRestaurant.getName(), restaurant.getName());
    }

    @Test
    public void searching_for_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        //WRITE UNIT TEST CASE HERE
        // Arrange
        String nonExistingRestaurantName = "Not Amelie's cafe";

        // Act and Assert
        assertThrows(restaurantNotFoundException.class, ()-> { service.findRestaurantByName(nonExistingRestaurantName); });
    }
    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>




    //>>>>>>>>>>>>>>>>>>>>>>ADMIN: ADDING & REMOVING RESTAURANTS<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void remove_restaurant_should_reduce_list_of_restaurants_size_by_1() throws restaurantNotFoundException {
        // Arrange
        String restaurantName = "Amelie's cafe";
        int initialNumberOfRestaurants = service.getRestaurants().size();

        // Act
        service.removeRestaurant(restaurantName);

        // Assert
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void removing_restaurant_that_does_not_exist_should_throw_exception() throws restaurantNotFoundException {
        // Arrange
        String nonExistingRestaurantName = "Pantry d'or";

        // Act and Assert
        assertThrows(restaurantNotFoundException.class,()->service.removeRestaurant(nonExistingRestaurantName));
    }

    @Test
    public void add_restaurant_should_increase_list_of_restaurants_size_by_1() {

        // Arrange
        int initialNumberOfRestaurants = service.getRestaurants().size();

        // Act
        service.addRestaurant("Pumpkin Tales","Chennai",LocalTime.parse("12:00:00"),LocalTime.parse("23:00:00"));

        // Assert
        assertEquals(initialNumberOfRestaurants + 1,service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}