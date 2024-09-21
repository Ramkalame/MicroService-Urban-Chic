import { computed, Injectable, signal } from '@angular/core';
import { Widget } from '../models/dashboard.model';
import { ProductsWidgetComponent } from '../components/dashboard-pages/products-widget/products-widget.component';
import { OrderWidgetComponent } from '../components/dashboard-pages/order-widget/order-widget.component';
import { WidgetReturnComponent } from '../components/dashboard-pages/widget-return/widget-return.component';
import { WidgetSalesComponent } from '../components/dashboard-pages/widget-sales/widget-sales.component';
import { WidgetTopComponent } from '../components/dashboard-pages/widget-top/widget-top.component';
import { RevenueChartComponent } from '../components/dashboard-pages/revenue-chart/revenue-chart.component';
import { OrdersChartComponent } from '../components/dashboard-pages/orders-chart/orders-chart.component';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  widgets = signal<Widget[]>([
    {
      id: 1,
      icon:'notes',
      label: 'Summary',
      content: WidgetTopComponent
    },
    {
      id: 2,
      icon:'local_shipping',
      label: 'Active Orders',
      content: OrderWidgetComponent
    },
    {
      id: 3,
      icon:'import_export',
      label: 'Returns',
      content: WidgetReturnComponent
    },
    {
      id: 4,
      icon:'notes',
      label: 'Sales',
      content: WidgetSalesComponent
    },
    {
      id: 5,
      icon:'local_mall',
      label: 'Products',
      content: ProductsWidgetComponent
    },
    {
      id: 9,
      icon:'show_chart',
      label: 'Revenue Chart',
      content: RevenueChartComponent
    },
    {
      id: 10,
      icon:'show_chart',
      label: 'Orders Chart',
      content: OrdersChartComponent
    },
   
  ])

  addedWidgets = signal<Widget[]>([
    {
      id: 1,
      icon:'notes',
      label: 'Summary',
      content: WidgetTopComponent
    },
    {
      id: 2,
      icon:'local_shipping',
      label: 'Active Orders',
      content: OrderWidgetComponent
    },
    {
      id: 3,
      icon:'import_export',
      label: 'Returns',
      content: WidgetReturnComponent
    },
    {
      id: 5,
      icon:'local_mall',
      label: 'Products',
      content: ProductsWidgetComponent
    },
    {
      id: 4,
      icon:'notes',
      label: 'Sales',
      content: WidgetSalesComponent
    },
    {
      id: 9,
      icon:'show_chart',
      label: 'Revenue Chart',
      content: RevenueChartComponent
    },
    {
      id: 10,
      icon:'show_chart',
      label: 'Orders Chart',
      content: OrdersChartComponent
    },
    
  ]);

  widgetsToAdd = computed(() => {
    const addedIds = this.addedWidgets().map(w => w.id);
    return this.widgets().filter(w =>!addedIds.includes(w.id));
  })

  addWidget(w: Widget) {
    this.addedWidgets.set([...this.addedWidgets(),{...w}]);
  }

  removeWidget(id: number) {
    this.addedWidgets.set(this.addedWidgets().filter(w => w.id!== id));
  }

  constructor() { }
}
