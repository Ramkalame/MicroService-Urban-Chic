import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-categories',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatIconModule, MatMenuModule],
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.css',
})
export class CategoriesComponent {
  mobiles = [
    'Smartphones',
    'Feature Phones',
    'Mobile Accessories',
    'Tablets',
    'Power Banks',
    'Phone Cases & Covers',
    'Screen Protectors',
    'Chargers & Cables',
    'Headphones & Earphones',
    'Smartwatches',
  ];

  electronics = [
    'Laptops & Computers',
    'Cameras',
    'Audio Devices',
    'Wearable Devices',
    'Computer Accessories',
    'Televisions & Video',
    'Gaming',
    'Smart Home Devices',
  ];

  //sub menus of elctronics subcategories

  laptopAndComputer = ["Laptops", "Desktops", "Monitors", "Gaming Laptops", "2-in-1 Laptops"]
  cameras = ["DSLR Cameras", "Point & Shoot Cameras", "Smartphone Cameras", "Video Cameras", "Photography Accessories"]
  audioDevices = ["Headphones", "Speakers", "Bluetooth Headsets", "Headphones Accessories", "Audio Amplifiers"]
  wearableDevices = ["Smartwatches", "Wearable Technology", "Smart Bracelets", "Wristbands", "Smart Glasses"]
  computerAccessories = ["Mouse", "Keyboard", "Mousepads", "External Hard Drives", "Monitor Cables", "Networking Devices"]
  televisionsAndVideo = ["Televisions", "Monitors", "Projectors", "Home Theater Systems", "Soundbars", "Surround Sound Systems", "TV & Audio Accessories"]
  gaming = ["Consoles", "Accessories", "Peripherals", "Nintendo Switch", "PlayStation 5", "Xbox Series X", "PC Gaming", "Consoles & Accessories"]
  smartHomeDevices = ["Smart TVs", "Smart Thermostats", "Smart Locks", "Smart Sensors", "Smart Home Security", "Smart Home Cameras", "Smart Home Appliances"]

  //

  fashion = [
    "Women's Clothing",
    "Men's Clothing",
    "Kids' Clothing",
    'Footwear',
    'Sportswear',
    'Innerwear & Sleepwear',
    'Accessories'
  ];

  //sub menus of fashion subcategories
  menClothing = ["Shirts", "T-Shirts", "Jeans", "Jackets", "Suits", "Activewear"]
  womenClothing = ["Dresses", "Tops", "Jeans", "Skirts", "Ethnic Wear", "Activewear"]
  kidsClothing = ["T-Shirts", "Shorts", "Dresses", "School Uniforms", "Sleepwear"]
  footwear = ["Sneakers", "Formal Shoes", "Sandals", "Boots", "Heels", "Slippers"]
  sportswear = ["Track Pants", "Sports T-Shirts", "Gym Shorts", "Yoga Pants", "Jackets"]
  innerwearAndSleepwear = ["Pajamas & Blazers", "Socks", "Underwear", "Swimwear", "Sportswear", "Sleepwear"]
  accessories = ["Watches", "Bags & Backpacks", "Jewelry", "Belts", "Wallets", "Sunglasses"]
  

  //

   homeAndKitchen = [
    "Furniture",
    "Home Decor",
    "Kitchen Appliances",
    "Cookware & Dining",
    "Bedding & Linens",
    "Storage & Organization",
    "Cleaning Supplies",
    "Lighting",
    "Tools & Home Improvement",
    "Bathroom Accessories",
    "Gardening Tools"
];

 sportsAndOutdoors = [
  "Sports Equipment",
  "Fitness Accessories",
  "Gym Equipment",
  "Outdoor Gear",
  "Cycling",
  "Camping & Hiking",
  "Running & Walking Gear",
  "Team Sports",
  "Yoga Mats & Accessories",
  "Water Sports",
  "Sportswear",
  "Footwear (Sports Shoes, Hiking Boots)"
];

 books = [
  "Fiction",
  "Non-Fiction",
  "Comics & Graphic Novels",
  "Educational & Academic",
  "Children's Books",
  "Biographies & Memoirs",
  "Science & Technology",
  "Self-Help",
  "Cookbooks",
  "Business & Economics",
  "Art & Photography"
];

 toys = [
  "Action Figures",
  "Educational Toys",
  "Dolls & Dollhouses",
  "Puzzles & Board Games",
  "Remote Control Toys",
  "Building Blocks & Construction Toys",
  "Plush Toys",
  "Outdoor Toys",
  "Art & Craft Supplies",
  "Ride-on Toys",
  "STEM Toys"
];

 petCare = [
  "Pet Food",
  "Pet Toys",
  "Pet Grooming Supplies",
  "Pet Beds & Furniture",
  "Pet Clothing & Accessories",
  "Pet Health & Wellness",
  "Pet Bowls & Feeders",
  "Leashes & Collars",
  "Aquariums & Fish Care",
  "Cat Litter & Accessories"
];

}
