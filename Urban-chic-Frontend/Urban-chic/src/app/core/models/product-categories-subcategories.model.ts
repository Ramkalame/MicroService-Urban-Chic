// category-data.ts

export interface Category {
    categoryName: string;
    subcategories: Subcategory[];
  }
  
  export interface Subcategory {
    subcategoryName: string;
    types: string[];
  }
  
  // Array of categories, subcategories, and types
  export const productCategories: Category[] = [
    {
        categoryName: 'Electronics',
        subcategories: [
          { subcategoryName: 'Mobile Phones', types: ['Smartphone', 'Feature Phone', 'Phablets'] },
          { subcategoryName: 'Laptops', types: ['Gaming Laptop', 'Ultrabook', '2-in-1 Laptops'] },
          { subcategoryName: 'Televisions', types: ['LED', 'OLED', '4K UHD'] },
          { subcategoryName: 'Headphones', types: ['Over-Ear', 'In-Ear', 'Bluetooth'] }
        ]
      },
      {
        categoryName: 'Clothing',
        subcategories: [
          { subcategoryName: 'Men', types: ['Shirts', 'Trousers'] },
          { subcategoryName: 'Women', types: ['Dresses', 'Tops', 'Skirts'] },
          { subcategoryName: 'Footwear', types: ['Casual Shoes', 'Formal Shoes', 'Sports Shoes'] },
          { subcategoryName: 'Accessories', types: ['Bags', 'Sunglasses', 'Watches'] }
        ]
      },
      {
        categoryName: 'Home & Kitchen',
        subcategories: [
          { subcategoryName: 'Furniture', types: ['Sofas', 'Tables', 'Chairs'] },
          { subcategoryName: 'Appliances', types: ['Refrigerators', 'Washing Machines', 'Microwaves'] },
          { subcategoryName: 'Cookware', types: ['Pots', 'Pans', 'Bakeware'] },
          { subcategoryName: 'Bedding', types: ['Sheets', 'Pillows', 'Blankets'] }
        ]
      },
      {
        categoryName: 'Beauty & Personal Care',
        subcategories: [
          { subcategoryName: 'Skincare', types: ['Moisturizers', 'Serums', 'Sunscreens'] },
          { subcategoryName: 'Haircare', types: ['Shampoos', 'Conditioners', 'Hair Oils'] },
          { subcategoryName: 'Makeup', types: ['Foundations', 'Lipsticks', 'Mascaras'] },
          { subcategoryName: 'Fragrances', types: ['Perfumes', 'Eau de Toilettes', 'Body Sprays'] }
        ]
      },
      {
        categoryName: 'Sports & Outdoors',
        subcategories: [
          { subcategoryName: 'Fitness Equipment', types: ['Treadmills', 'Dumbbells', 'Yoga Mats'] },
          { subcategoryName: 'Outdoor Gear', types: ['Tents', 'Sleeping Bags', 'Hiking Boots'] },
          { subcategoryName: 'Cycling', types: ['Bicycles', 'Helmets', 'Cycling Apparel'] },
          { subcategoryName: 'Team Sports', types: ['Soccer Balls', 'Basketballs', 'Baseball Bats'] }
        ]
      },
      {
        categoryName: 'Books',
        subcategories: [
          { subcategoryName: 'Fiction', types: ['Novels', 'Short Stories', 'Science Fiction'] },
          { subcategoryName: 'Non-Fiction', types: ['Biographies', 'Self-Help', 'History'] },
          { subcategoryName: 'Educational', types: ['Textbooks', 'Workbooks', 'Reference'] },
          { subcategoryName: 'Children\'s Books', types: ['Picture Books', 'Early Readers', 'Chapter Books'] }
        ]
      },
      {
        categoryName: 'Automotive',
        subcategories: [
          { subcategoryName: 'Car Accessories', types: ['Seat Covers', 'Floor Mats', 'Car Cleaning'] },
          { subcategoryName: 'Motorcycle Gear', types: ['Helmets', 'Jackets', 'Gloves'] },
          { subcategoryName: 'Tools & Equipment', types: ['Jacks', 'Wrenches', 'Toolkits'] },
          { subcategoryName: 'Car Care', types: ['Car Wash', 'Wax', 'Polish'] }
        ]
      },
      {
        categoryName: 'Toys & Games',
        subcategories: [
          { subcategoryName: 'Action Figures', types: ['Superheroes', 'Dinosaurs', 'Robots'] },
          { subcategoryName: 'Board Games', types: ['Strategy Games', 'Family Games', 'Trivia Games'] },
          { subcategoryName: 'Puzzles', types: ['Jigsaw Puzzles', '3D Puzzles', 'Puzzle Cubes'] },
          { subcategoryName: 'Educational Toys', types: ['Building Blocks', 'STEM Kits', 'Learning Games'] }
        ]
      },
      {
        categoryName: 'Jewelry',
        subcategories: [
          { subcategoryName: 'Necklaces', types: ['Chains', 'Lockets', 'Chokers'] },
          { subcategoryName: 'Rings', types: ['Engagement Rings', 'Wedding Bands', 'Fashion Rings'] },
          { subcategoryName: 'Bracelets', types: ['Cuffs', 'Bangles', 'Charms'] },
          { subcategoryName: 'Earrings', types: ['Studs', 'Hoops', 'Drops'] }
        ]
      },
      {
        categoryName: 'Office Supplies',
        subcategories: [
          { subcategoryName: 'Stationery', types: ['Notebooks', 'Pens', 'Papers'] },
          { subcategoryName: 'Office Furniture', types: ['Desks', 'Chairs', 'Storage'] },
          { subcategoryName: 'Technology', types: ['Printers', 'Monitors', 'Keyboards'] },
          { subcategoryName: 'Organizers', types: ['Planners', 'Folders', 'Baskets'] }
        ]
      },
      {
        categoryName: 'Pet Supplies',
        subcategories: [
          { subcategoryName: 'Dog Supplies', types: ['Food', 'Toys', 'Beds'] },
          { subcategoryName: 'Cat Supplies', types: ['Litter Boxes', 'Scratching Posts', 'Toys'] },
          { subcategoryName: 'Bird Supplies', types: ['Cages', 'Food', 'Toys'] },
          { subcategoryName: 'Fish Supplies', types: ['Aquariums', 'Food', 'Filters'] }
        ]
      }
  ];
  